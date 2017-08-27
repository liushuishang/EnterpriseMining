package enterprise.mining.data.model.relationship;

import enterprise.mining.data.model.node.EntityNode;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * 子公司分支关系
 * Created by  yuananyun on 2017/8/27.
 */
@RelationshipEntity(type ="分支机构")
public class BranchRelationship extends ERelationship {

    public BranchRelationship() {
    }

    public BranchRelationship(String id, EntityNode startNode, EntityNode endNode) {
        super(id, startNode, endNode);
    }
}
