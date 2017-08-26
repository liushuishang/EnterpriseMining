package enterprise.mining.data.model.node;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;

/**
 * 企业
 * Created by  yuananyun on 2017/8/26.
 */
public abstract class EntityNode {
    @GraphId
    private Long nodeId;
    @Index(unique = true, primary = true)
    private String entityId;
    @Index
    private String name;
    private String ntype;

    public EntityNode() {
    }

    public String getNodeType() {
        return null;
    }

    public EntityNode(String entityId, String name, String ntype) {
        this.entityId = entityId;
        this.name = name;
        this.ntype = ntype;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype;
    }
}
