package enterprise.mining.data.service;

import com.alibaba.fastjson.JSON;
import enterprise.mining.data.model.node.Company;
import enterprise.mining.data.model.node.EntityNode;
import enterprise.mining.data.model.node.Person;
import enterprise.mining.data.model.relationship.ERelationship;
import enterprise.mining.data.model.relationship.InvestCRelationship;
import enterprise.mining.data.model.relationship.OwnRelationship;
import enterprise.mining.data.model.relationship.StaffRelationship;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by  yuananyun on 2017/8/26.
 */
@Service
public class GraphBuildService {
    private Logger logger = LoggerFactory.getLogger(GraphBuildService.class);

    private static final String MAP_COLLECTION_NAME = "map_info";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EnterpriseGraphService graphService;

//    db.map_info.find({id:'2357744233'}).limit(1)
//        db.base_info.find({}).skip(100).limit(10)

    @SuppressWarnings("all")
    @Transactional
    public void createOneMap(String targetId) {
        Map<String, Object> data = mongoTemplate.findOne(new Query(Criteria.where("id").is(String.valueOf(targetId))),
                Map.class, MAP_COLLECTION_NAME);
        if (data == null || data.size() == 0) return;

        Company company = new Company();
        company.setEntityId(targetId);
        if (createNodes(data)) return;
        if (createRelationships(data, company)) return;
        graphService.saveCompanyNode(company);
    }

    private boolean createNodes(Map<String, Object> data) {
        List<Map<String, Object>> nodes = (List<Map<String, Object>>) MapUtils.getObject(data, "nodes");
        if (nodes == null || nodes.size() == 0) return true;
        for (Map<String, Object> node : nodes) {
            createNode(node);
        }
        return false;
    }

    private boolean createRelationships(Map<String, Object> data, Company company) {
        List<Map<String, Object>> relationships = (List<Map<String, Object>>) MapUtils.getObject(data, "relationships");
        if (relationships == null || relationships.size() == 0) return true;

        Set<ERelationship> investorRelationshipList = new HashSet<>();
        Set<ERelationship> investmentRelationshipList = new HashSet<>();
        Set<ERelationship> staffRelationshipList = new HashSet<>();

        for (Map<String, Object> param : relationships) {
            if (param == null || param.size() == 0) continue;
            String startId = getId(param, "startNode");
            String endId = getId(param, "endNode");
            if (StringUtils.isBlank(startId) || StringUtils.isBlank(endId)) continue;

            EntityNode startNode = graphService.getEntityNode(startId);
            EntityNode endNode = graphService.getEntityNode(endId);
            if (startNode == null || endNode == null) continue;

            String type = getMapString(param, "type");
            if (StringUtils.isBlank(type)) continue;

            ERelationship relationship = null;
            Map<String, Object> properties = getProperties(param);
            switch (type) {
                case "OWN":
                    relationship = createOwnRelationship(startNode, endNode, properties);
                    company.setOwnRelationship(relationship);
                    break;
                case "INVEST_C":
                case "INVEST_H":
                    relationship = createInvestRelationship(startNode, endNode, properties);
                    investorRelationshipList.add(relationship);
                    break;
                case "SERVE":
                    relationship = createStaffRelationship(startNode, endNode, properties);
                    staffRelationshipList.add(relationship);
                    break;
                case "BRANCH":
                    break;
                default:
                    logger.info("[createRelationships] 未知的关系类型{}", type);
            }
            //保存关系
            graphService.saveRelationship(relationship);
        }
        company.setInvestorRelationshipList(investorRelationshipList);
        company.setInvestmentRelationshipList(investmentRelationshipList);
        company.setStaffRelationshipList(staffRelationshipList);
        return false;
    }

    /**
     * 创建关系节点
     *
     * @param param 如：{"id":2.9510338E7,"properties":{"name":"广东俊特团贷网络信息服务股份有限公司","ntype":"f"},"labels":["Company"]}
     */
    private void createNode(Map<String, Object> param) {
        if (param == null || param.size() == 0) return;
        String id = getId(param, "id");
        //判断该节点是否存在，存在则跳过
        if (graphService.isNodeExists(id)) return;

        Map<String, Object> properties = getProperties(param);
        String name = MapUtils.getString(properties, "name");

        String ntype = MapUtils.getString(properties, "ntype");
        String labels = MapUtils.getString(param, "labels", "");

        if (labels.contains("Company")) {
            Company company = new Company(id, name, ntype, null);
            graphService.saveCompanyNode(company);
        } else if (labels.contains("Human")) {
            Person person = new Person(id, name, ntype);
            graphService.savePersonNode(person);
        } else {
            logger.info("[createNode] 漏网之鱼：{}", JSON.toJSONString(param));
        }
    }


    /**
     * 创建任职关系
     *
     * @param startNode
     * @param endNode
     * @param properties
     * @return
     */
    private ERelationship createStaffRelationship(EntityNode startNode, EntityNode endNode, Map<String, Object> properties) {
        try {
            return new StaffRelationship(
                    getId(properties, "id"),
                    startNode, endNode,
                    getMapString(properties, "companyId"),
                    getMapString(properties, "staffTypeName"),
                    getId(properties, "staffId"));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 创建股东关系
     *
     * @param startNode
     * @param endNode
     * @param properties
     */
    private ERelationship createInvestRelationship(EntityNode startNode, EntityNode endNode, Map<String, Object> properties) {
        try {
            return new InvestCRelationship(
                    getId(properties, "id"),
                    startNode, endNode,
                    getMapString(properties, "companyId"),
                    getId(properties, "investorId"),
                    getMapString(properties, "investorType"),
                    getMapString(properties, "capital"), getMapString(properties, "capitalActl"));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 创建法人关系
     *
     * @param startNode
     * @param endNode
     * @param properties
     */
    private ERelationship createOwnRelationship(EntityNode startNode, EntityNode endNode, Map<String, Object> properties) {
        try {
            return new OwnRelationship(
                    getId(properties, "id"),
                    startNode, endNode);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }


    /**
     * 获取实体对象Id
     *
     * @param param
     * @return
     */
    private String getId(Map<String, Object> param, String field) {
        return String.valueOf(MapUtils.getLongValue(param, field));
    }

    /**
     * 获取属性Map
     *
     * @param param
     * @return
     */
    private Map<String, Object> getProperties(Map<String, Object> param) {
        return (Map<String, Object>) MapUtils.getMap(param, "properties");
    }

    /**
     * 获取map中的String字段值
     *
     * @param param
     * @param filed
     * @return
     */
    private String getMapString(Map<String, Object> param, String filed) {
        return MapUtils.getString(param, filed, "").trim();
    }

}
