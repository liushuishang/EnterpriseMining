package enterprise.mining.data.model.relationship;

import enterprise.mining.data.model.node.EntityNode;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * 任职关系
 * Created by  yuananyun on 2017/8/26.
 */
@RelationshipEntity(type ="任职")
public class StaffRelationship extends ERelationship {

    private String companyId;
    //职务名称
    private String staffTypeName;
    private  String staffId;

    public StaffRelationship() {
    }

    public StaffRelationship(String id, EntityNode startNode, EntityNode endNode,
                             String companyId, String staffTypeName, String staffId) {
        super(id, startNode, endNode);
        this.companyId = companyId;
        this.staffTypeName = staffTypeName;
        this.staffId = staffId;
        this.setType(ERelationshipType.STAFF.getType());
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getStaffTypeName() {
        return staffTypeName;
    }

    public void setStaffTypeName(String staffTypeName) {
        this.staffTypeName = staffTypeName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
