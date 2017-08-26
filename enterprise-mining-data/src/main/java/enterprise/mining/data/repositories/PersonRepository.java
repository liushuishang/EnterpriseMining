package enterprise.mining.data.repositories;

import enterprise.mining.data.model.node.Person;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by  yuananyun on 2017/8/26.
 */
public interface PersonRepository extends GraphRepository<Person> {

    Person findOneByEntityId(String entityId);

}
