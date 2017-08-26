package enterprise.mining.data.model.relationship;

import enterprise.mining.data.model.node.EntityNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by  yuananyun on 2017/8/26.
 */
public abstract class ERelationship {

    @GraphId
    private Long relationshipId;
    @Index(unique = true, primary = true)
    private String sid;
    @StartNode
    private EntityNode startNode;
    @EndNode
    private EntityNode endNode;
    //关系的类型
    private String type;

    public ERelationship() {
    }

    public ERelationship(String id, EntityNode startNode, EntityNode endNode) {
        this.sid = id;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public EntityNode getStartNode() {
        return startNode;
    }

    public void setStartNode(EntityNode startNode) {
        this.startNode = startNode;
    }

    public EntityNode getEndNode() {
        return endNode;
    }

    public void setEndNode(EntityNode endNode) {
        this.endNode = endNode;
    }
}
