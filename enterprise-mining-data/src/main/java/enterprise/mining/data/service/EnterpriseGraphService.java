package enterprise.mining.data.service;

import enterprise.mining.data.model.node.Company;
import enterprise.mining.data.model.node.EntityNode;
import enterprise.mining.data.model.node.Person;
import enterprise.mining.data.model.relationship.ERelationship;
import enterprise.mining.data.repositories.CompanyRepository;
import enterprise.mining.data.repositories.ERelationshipRepository;
import enterprise.mining.data.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by  yuananyun on 2017/8/26.
 */
@Service
public class EnterpriseGraphService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ERelationshipRepository relationshipRepository;

    /**
     * 保存公司节点
     *
     * @param company
     */
    public void saveCompanyNode(Company company) {
        if (company == null) return;
        companyRepository.save(company);
    }

    public void savePersonNode(Person person) {
        if (person == null) return;
        personRepository.save(person);
    }

//    public void saveNode(EntityNode nodeEntity) {
//        if (nodeEntity == null) return;
//        if ("Company".equals(nodeEntity.getNodeType()))
//            saveCompanyNode((Company) nodeEntity);
//        else if ("Person".equals(nodeEntity.getNodeType()))
//            savePersonNode((Person) nodeEntity);
//    }


    /**
     * 判断某个node是否存在
     * @param id
     * @return
     */
    public boolean isNodeExists(String id) {
        if(id==null) return false;
        return false;
    }

    /**
     * 根据id查找一个node节点
     * @param entityId
     * @return
     */
    public EntityNode getEntityNode(String entityId) {
        EntityNode node=companyRepository.findOneByEntityId(entityId);
        if(node==null)
            node=personRepository.findOneByEntityId(entityId);
        return node;
    }

    /**
     * 保存一个关系
     * @param relationship
     */
    public void saveRelationship(ERelationship relationship) {
        if(relationship==null) return;
        relationshipRepository.save(relationship);
    }
}
