package enterprise.mining.data.service;

import com.alibaba.fastjson.JSON;
import enterprise.mining.data.model.node.Company;
import enterprise.mining.data.model.node.EntityNode;
import enterprise.mining.data.model.node.Person;
import enterprise.mining.data.model.relationship.*;
import enterprise.mining.data.utils.EMapUtils;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static enterprise.mining.data.utils.EMapUtils.*;

/**
 * Created by  yuananyun on 2017/8/26.
 */
@Service
public class GraphBuildService {
    private Logger logger = LoggerFactory.getLogger(GraphBuildService.class);

    private static final String MAP_COLLECTION_NAME = "map_info";
    private static final String MAP_ID_COLLECTION_NAME = "map_ids";


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EnterpriseGraphService graphService;

    /**
     * 全量构建
     */
    public void build() {
        Map data = null;
        do {
            try {
                data = mongoTemplate.findAndRemove(new Query(Criteria.where("_id").exists(true)), Map.class, MAP_ID_COLLECTION_NAME);
                String _id = EMapUtils.getString(data, "_id");
                createOneMap(_id, EMapUtils.getId(data, "entityId"));
                mongoTemplate.remove(new Query(Criteria.where("_id").is(_id)),MAP_ID_COLLECTION_NAME);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        } while (data != null);
    }


    @SuppressWarnings("all")
    @Transactional
    public void createOneMap(String _id, String targetId) {
        Map<String, Object> data = mongoTemplate.findOne(new Query(Criteria.where("id").is(String.valueOf(targetId))),
                Map.class, MAP_COLLECTION_NAME);
        if (data == null || data.size() == 0) return;

        List<Map<String, Object>> nodes = (List<Map<String, Object>>) MapUtils.getObject(data, "nodes");
        if (nodes == null || nodes.size() <10) return;//小于个节点的关系不考虑

        Company company = new Company();
        company.setEntityId(targetId);
        graphService.fillCompanyBaseInfo(company, _id, null);
        graphService.saveCompanyNode(company);

        createNodes(nodes);
        createRelationships(data);
    }

    private void createNodes(List<Map<String, Object>> nodes) {
        for (Map<String, Object> node : nodes) {
            createNode(node);
        }
    }

    private void createRelationships(Map<String, Object> data) {
        List<Map<String, Object>> relationships = (List<Map<String, Object>>) MapUtils.getObject(data, "relationships");
        if (relationships == null || relationships.size() == 0) return ;

        List<ERelationship> relationshipList = new ArrayList<>();

        for (Map<String, Object> param : relationships) {
            if (param == null || param.size() == 0) continue;
            String startId = getId(param, "startNode");
            String endId = getId(param, "endNode");
            if (StringUtils.isBlank(startId) || StringUtils.isBlank(endId)) continue;

            EntityNode startNode = graphService.getEntityNode(startId);
            EntityNode endNode = graphService.getEntityNode(endId);
            if (startNode == null || endNode == null) continue;

            String type = getString(param, "type");
            if (StringUtils.isBlank(type)) continue;

            ERelationship relationship = null;
            Map<String, Object> properties = getProperties(param);
            switch (type) {
                case "OWN":
                    relationship = createOwnRelationship(startNode, endNode, properties);
                    break;
                case "INVEST_H":
                    relationship = createStockholderRelationship(startNode, endNode, properties);
                    break;
                case "INVEST_C":
                    relationship = createInvestmentRelationship(startNode, endNode, properties);
                    break;
                case "SERVE":
                    relationship = createStaffRelationship(startNode, endNode, properties);
                    break;
                case "BRANCH":
                    relationship = createBranchRelationship(startNode, endNode, properties);
                    break;
                default:
                    logger.info("[createRelationships] 未知的关系类型{}", type);
            }
            //保存关系
            if (relationship != null)
                relationshipList.add(relationship);
        }
        if (relationshipList.size() > 0)
            graphService.saveRelationshipList(relationshipList);
    }


    /**
     * 创建关系节点
     *
     * @param param 如：{"id":2.9510338E7,"properties":{"name":"广东俊特团贷网络信息服务股份有限公司","ntype":"f"},"labels":["Company"]}
     */
    private void createNode(Map<String, Object> param) {
        if (param == null || param.size() == 0) return;
        String entityId = getId(param, "id");
        //判断该节点是否存在，存在则跳过
        if (graphService.isNodeExists(entityId)) return;

        Map<String, Object> properties = getProperties(param);
        String name = MapUtils.getString(properties, "name");

        String ntype = MapUtils.getString(properties, "ntype");
        String labels = MapUtils.getString(param, "labels", "");

        if (labels.contains("Company")) {
            Company company = new Company(entityId, name, ntype, null);
            graphService.saveCompanyNode(company);
        } else if (labels.contains("Human")) {
            Person person = new Person(entityId, name, ntype);
            graphService.savePersonNode(person);
        } else {
            logger.info("[createNode] 漏网之鱼：{}", JSON.toJSONString(param));
        }
    }


    /**
     * 创建分支机构关系
     *
     * @param startNode
     * @param endNode
     * @param properties
     * @return
     */
    private ERelationship createBranchRelationship(EntityNode startNode, EntityNode endNode, Map<String, Object> properties) {
        try {
            return new BranchRelationship(
                    getId(properties, "id"),
                    startNode, endNode
            );
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
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
                    getId(properties, "companyId"),
                    getString(properties, "staffTypeName"),
                    getId(properties, "staffId"));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 创建对外投资关系
     *
     * @param startNode
     * @param endNode
     * @param properties
     * @return
     */
    private ERelationship createInvestmentRelationship(EntityNode startNode, EntityNode endNode, Map<String, Object> properties) {
        try {
            return new InvestmentRelationship(
                    getId(properties, "id"),
                    startNode, endNode,
                    getId(properties, "companyId"),
                    getId(properties, "investorId"),
                    getString(properties, "investorType"),
                    getString(properties, "capital"), getString(properties, "capitalActl"));
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
    private ERelationship createStockholderRelationship(EntityNode startNode, EntityNode endNode, Map<String, Object> properties) {
        try {
            return new StockholderRelationship(
                    getId(properties, "id"),
                    startNode, endNode,
                    getId(properties, "companyId"),
                    getId(properties, "investorId"),
                    getString(properties, "investorType"),
                    getString(properties, "capital"), getString(properties, "capitalActl"));
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


}
