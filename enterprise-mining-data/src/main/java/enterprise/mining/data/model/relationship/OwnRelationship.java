package enterprise.mining.data.model.relationship;

import enterprise.mining.data.model.node.EntityNode;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**法人关系
 * Created by  yuananyun on 2017/8/26.
 */
@RelationshipEntity(type ="法人")
public class OwnRelationship extends ERelationship {

    public OwnRelationship() {
    }

    public OwnRelationship(String id, EntityNode startNode, EntityNode endNode) {
        super(id, startNode, endNode);
        this.setType(ERelationshipType.OWN.getType());
    }
}
