package enterprise.mining.data.repositories;

import enterprise.mining.data.model.node.Company;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by  yuananyun on 2017/8/26.
 */
public interface CompanyRepository extends GraphRepository<Company> {

    Company findOneByEntityId(String entityId);
}
