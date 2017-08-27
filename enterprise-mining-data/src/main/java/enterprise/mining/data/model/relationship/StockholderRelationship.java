package enterprise.mining.data.model.relationship;

import enterprise.mining.data.model.node.EntityNode;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**   参股关系
 * Created by  yuananyun on 2017/8/26.
 */
@RelationshipEntity(type ="股东")
public class StockholderRelationship extends ERelationship {
    private String companyId;
    private String investorId;
    private String investorType;
    private String capital;
    //实际支付
    private String capitalActl;

    public StockholderRelationship() {
    }

    public StockholderRelationship(String id, EntityNode startNode, EntityNode endNode,
                                   String companyId, String investorId, String investorType, String capital, String capitalActl) {
        super(id, startNode, endNode);
        this.companyId = companyId;
        this.investorId = investorId;
        this.investorType = investorType;
        this.capital = capital;
        this.capitalActl = capitalActl;
        this.setType(ERelationshipType.INVEST_C.getType());
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getInvestorType() {
        return investorType;
    }

    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapitalActl() {
        return capitalActl;
    }

    public void setCapitalActl(String capitalActl) {
        this.capitalActl = capitalActl;
    }
}
