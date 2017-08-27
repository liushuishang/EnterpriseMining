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
    private String historyName;
    private String email;
    private String companyOrgType;
    private String companyType;
    //证券名称
    private String boundName;
    private String bondType;
    private String boundNum;
//    工商注册号
    private String regNumber;
//    信用代码
    private String creditCode;
//    电话
    private String phoneNumber;
//    注册资本
    private String regCapital;
//    注册机构
    private String regInstitute;
//    注册地址
    private String regLocation;
//    批准时间
    private String approvedTime;
    private String industry;
    private String businessScope;
    private String regStatus;
    private String estiblishTime;
    private String legalPersonId;
    private String legalPersonName;
    //    营业期限
    private String fromTime;
    private String toTime;
    //所在地区
    private String base;
    private String updateTime;


    @Relationship(type = "法人",direction = Relationship.INCOMING)
    private ERelationship ownRelationship;
    @Relationship(type="股东",direction = Relationship.INCOMING)
    private Set<ERelationship> investorRelationshipSet;
    @Relationship(type="对外投资",direction = Relationship.OUTGOING)
    private Set<ERelationship> investmentRelationshipSet;
    @Relationship(type="任职",direction = Relationship.INCOMING)
    private Set<ERelationship> staffRelationshipSet;
    @Relationship(type="分支机构",direction = Relationship.OUTGOING)
    private Set<ERelationship> BranchRelationshipSet;


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

    public Set<ERelationship> getInvestorRelationshipSet() {
        return investorRelationshipSet;
    }

    public void setInvestorRelationshipSet(Set<ERelationship> investorRelationshipSet) {
        this.investorRelationshipSet = investorRelationshipSet;
    }

    public Set<ERelationship> getInvestmentRelationshipSet() {
        return investmentRelationshipSet;
    }

    public void setInvestmentRelationshipSet(Set<ERelationship> investmentRelationshipSet) {
        this.investmentRelationshipSet = investmentRelationshipSet;
    }

    public Set<ERelationship> getStaffRelationshipSet() {
        return staffRelationshipSet;
    }

    public void setStaffRelationshipSet(Set<ERelationship> staffRelationshipSet) {
        this.staffRelationshipSet = staffRelationshipSet;
    }

    public Set<ERelationship> getBranchRelationshipSet() {
        return BranchRelationshipSet;
    }

    public void setBranchRelationshipSet(Set<ERelationship> branchRelationshipSet) {
        BranchRelationshipSet = branchRelationshipSet;
    }

    public String getHistoryName() {
        return historyName;
    }

    public void setHistoryName(String historyName) {
        this.historyName = historyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyOrgType() {
        return companyOrgType;
    }

    public void setCompanyOrgType(String companyOrgType) {
        this.companyOrgType = companyOrgType;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getBoundName() {
        return boundName;
    }

    public void setBoundName(String boundName) {
        this.boundName = boundName;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getBoundNum() {
        return boundNum;
    }

    public void setBoundNum(String boundNum) {
        this.boundNum = boundNum;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getRegInstitute() {
        return regInstitute;
    }

    public void setRegInstitute(String regInstitute) {
        this.regInstitute = regInstitute;
    }

    public String getRegLocation() {
        return regLocation;
    }

    public void setRegLocation(String regLocation) {
        this.regLocation = regLocation;
    }

    public String getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getEstiblishTime() {
        return estiblishTime;
    }

    public void setEstiblishTime(String estiblishTime) {
        this.estiblishTime = estiblishTime;
    }

    public String getLegalPersonId() {
        return legalPersonId;
    }

    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }



}
