package enterprise.mining.data.model.relationship;

import enterprise.mining.data.model.node.EntityNode;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * 企业对外投资关系
 * Created by  yuananyun on 2017/8/27.
 */
@RelationshipEntity(type = "对外投资")
public class InvestmentRelationship extends StockholderRelationship {

    public InvestmentRelationship() {
    }

    public InvestmentRelationship(String id, EntityNode startNode, EntityNode endNode,
                                  String companyId, String investorId, String investorType, String capital, String capitalActl) {
        super(id, startNode, endNode, companyId, investorId, investorType, capital, capitalActl);
    }

}
