package enterprise.mining.data.model.node;

import enterprise.mining.data.model.relationship.ERelationship;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by  yuananyun on 2017/8/26.
 */
@NodeEntity(label = "Company")
public class Company extends EntityNode {

    private Integer type;

    //    简介
//    电话
//    网址
//    邮箱
//     地址
//    注册时间
//    注册资本
//    企业状态
//    工商注册号
//    组织机构代码
//    统一信用代码
//    企业类型
//    纳税人识别号
//    行业
//    营业期限
//    核准日期
//    登记机关
//    注册地址
//    英文名称
//    经营范围


    @Relationship(type = "法人",direction = Relationship.INCOMING)
    private ERelationship ownRelationship;
    @Relationship(type="股东",direction = Relationship.INCOMING)
    private Set<ERelationship> investorRelationshipList;
    @Relationship(type="对外投资",direction = Relationship.INCOMING)
    private Set<ERelationship> investmentRelationshipList;
    @Relationship(type="任职",direction = Relationship.INCOMING)
    private Set<ERelationship> staffRelationshipList;


    public Company() {
    }

    public Company(String id, String name, String ntype, Integer type) {
        super(id, name, ntype);
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ERelationship getOwnRelationship() {
        return ownRelationship;
    }

    public void setOwnRelationship(ERelationship ownRelationship) {
        this.ownRelationship = ownRelationship;
    }

    public Set<ERelationship> getInvestorRelationshipList() {
        return investorRelationshipList;
    }

    public void setInvestorRelationshipList(Set<ERelationship> investorRelationshipList) {
        this.investorRelationshipList = investorRelationshipList;
    }

    public Set<ERelationship> getInvestmentRelationshipList() {
        return investmentRelationshipList;
    }

    public void setInvestmentRelationshipList(Set<ERelationship> investmentRelationshipList) {
        this.investmentRelationshipList = investmentRelationshipList;
    }

    public Set<ERelationship> getStaffRelationshipList() {
        return staffRelationshipList;
    }

    public void setStaffRelationshipList(Set<ERelationship> staffRelationshipList) {
        this.staffRelationshipList = staffRelationshipList;
    }
}
