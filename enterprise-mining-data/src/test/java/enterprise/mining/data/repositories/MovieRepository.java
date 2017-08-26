package enterprise.mining.data.repositories;

import enterprise.mining.data.model.Movie;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by  yuananyun on 2017/8/13.
 */
public interface MovieRepository extends GraphRepository<Movie> {
}
