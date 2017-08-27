package enterprise.mining.data.service;

import enterprise.mining.data.model.node.Company;
import enterprise.mining.data.model.node.EntityNode;
import enterprise.mining.data.model.node.Person;
import enterprise.mining.data.model.relationship.ERelationship;
import enterprise.mining.data.repositories.CompanyRepository;
import enterprise.mining.data.repositories.ERelationshipRepository;
import enterprise.mining.data.repositories.PersonRepository;
import enterprise.mining.data.utils.EMapUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by  yuananyun on 2017/8/26.
 */
@Service
public class EnterpriseGraphService {
    private static String ENTERPRISE_INFO_COLLECTION = "base_info";

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ERelationshipRepository relationshipRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存公司节点
     *
     * @param company
     */
    public Company saveCompanyNode(Company company) {
        if (company == null) return company;
        Long nodeId = company.getNodeId();
        if (nodeId == null) {
            String entityId = company.getEntityId();
            String name = company.getName();
            fillCompanyBaseInfo(company, entityId, name);
        }
        return companyRepository.save(company);
    }


    public void savePersonNode(Person person) {
        if (person == null) return;
        personRepository.save(person);
    }


    /**
     * 判断某个node是否存在
     *
     * @param entityId
     * @return
     */
    public boolean isNodeExists(String entityId) {
        if (entityId == null) return false;
        if (companyRepository.findOneByEntityId(entityId) != null) return true;
        if (personRepository.findOneByEntityId(entityId) != null) return true;
        return false;
    }

    /**
     * 根据id查找一个node节点
     *
     * @param entityId
     * @return
     */
    public EntityNode getEntityNode(String entityId) {
        EntityNode node = companyRepository.findOneByEntityId(entityId);
        if (node == null)
            node = personRepository.findOneByEntityId(entityId);
        return node;
    }

    /**
     * 保存一个关系
     *
     * @param relationship
     */
    public void saveRelationship(ERelationship relationship) {
        if (relationship == null) return;
        relationshipRepository.save(relationship);
    }

    /**
     * 批量插入关系
     *
     * @param relationshipList
     */
    public void saveRelationshipList(List<ERelationship> relationshipList) {
        if (relationshipList == null) return;
        relationshipRepository.save(relationshipList);
    }

    /**
     * 填充公司的基本信息
     *
     * @param company
     * @param recordId
     * @param name
     */
    public void fillCompanyBaseInfo(Company company, String recordId, String name) {
        if (company == null) return;
        //继续完善公司信息
        String _id = recordId;
        if (_id == null)
            _id = DigestUtils.md5Hex(name);
        Map data = mongoTemplate.findById(_id, Map.class, ENTERPRISE_INFO_COLLECTION);

        if (data != null) {
            Map baseInfo = MapUtils.getMap(data, "baseInfo");
            if (baseInfo == null) return;

            company.setName(EMapUtils.getString(baseInfo,"name"));
            company.setFromTime(EMapUtils.getDateTime(baseInfo, "fromTime"));
            company.setToTime(EMapUtils.getDateTime(baseInfo, "toTime"));
            company.setType(EMapUtils.getInteger(baseInfo, "type"));
            company.setRegNumber(EMapUtils.getString(baseInfo, "regNumber"));
            company.setPhoneNumber(EMapUtils.getString(baseInfo, "phoneNumber"));
            company.setRegCapital(EMapUtils.getString(baseInfo, "regCapital"));
            company.setRegInstitute(EMapUtils.getString(baseInfo, "regInstitute"));
            company.setRegLocation(EMapUtils.getString(baseInfo, "regLocation"));
            company.setIndustry(EMapUtils.getString(baseInfo, "industry"));
            company.setApprovedTime(EMapUtils.getDateTime(baseInfo, "approvedTime"));
            company.setBusinessScope(EMapUtils.getString(baseInfo, "businessScope"));
            company.setEstiblishTime(EMapUtils.getDateTime(baseInfo, "estiblishTime"));
            company.setRegStatus(EMapUtils.getString(baseInfo, "regStatus"));
            company.setLegalPersonId(EMapUtils.getId(baseInfo, "legalPersonId"));
            company.setLegalPersonName(EMapUtils.getString(baseInfo, "legalPersonName"));
            company.setEmail(EMapUtils.getString(baseInfo, "email"));
            company.setCompanyOrgType(EMapUtils.getString(baseInfo, "companyOrgType"));
            company.setBase(EMapUtils.getString(baseInfo, "base"));
            company.setCreditCode(EMapUtils.getString(baseInfo, "creditCode"));
            company.setHistoryName(EMapUtils.getString(baseInfo, "historyName"));
            company.setUpdateTime(EMapUtils.getString(baseInfo, "updateTime"));

        }
    }

}
