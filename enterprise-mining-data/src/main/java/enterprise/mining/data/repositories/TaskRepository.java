package enterprise.mining.data.repositories;

import enterprise.mining.data.domain.Task;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by  yuananyun on 2017/7/30.
 */
@Repository
public interface TaskRepository extends GraphRepository<Task> {

    Task findByTaskName(@Param("taskName") String taskName);

    @Query("MATCH (t:Task) WHERE t.taskName =~ ('(?i).*'+{taskName}+'.*') RETURN t")
    Collection<Task> findByNameContaining(@Param("taskName") String taskName);

}

