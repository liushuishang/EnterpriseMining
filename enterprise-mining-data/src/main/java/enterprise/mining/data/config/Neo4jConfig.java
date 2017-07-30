package enterprise.mining.data.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by  yuananyun on 2017/7/29.
 */
@Configuration
@EnableNeo4jRepositories("cn.didadu.sdn.repository")
@EnableTransactionManagement
public class Neo4jConfig  {
}
