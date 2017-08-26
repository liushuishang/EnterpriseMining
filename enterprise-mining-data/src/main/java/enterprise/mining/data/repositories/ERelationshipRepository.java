package enterprise.mining.data.repositories;

import enterprise.mining.data.model.relationship.ERelationship;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by  yuananyun on 2017/8/27.
 */
public interface ERelationshipRepository extends GraphRepository<ERelationship> {
}
