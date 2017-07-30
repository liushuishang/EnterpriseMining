package enterprise.mining.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ImportResource(locations = {"classpath*:spring/*.xml"})
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "enterprise.mining.data.repositories")
public class EnterpriseMiningDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseMiningDataApplication.class, args);
	}
}
