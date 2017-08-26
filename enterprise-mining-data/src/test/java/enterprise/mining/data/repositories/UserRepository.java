package enterprise.mining.data.repositories;

import enterprise.mining.data.model.User;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by  yuananyun on 2017/8/13.
 */
public interface UserRepository extends GraphRepository<User> {

    User findByUserId(String userId);
}
