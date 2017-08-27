package enterprise.mining.data.tests;

import enterprise.mining.data.EnterpriseMiningDataApplication;
import enterprise.mining.data.model.node.Company;
import enterprise.mining.data.repositories.CompanyRepository;
import enterprise.mining.data.service.GraphBuildService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by  yuananyun on 2017/8/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnterpriseMiningDataApplication.class)
@Rollback(value = false)
public class GraphBuildServiceTest {

    @Autowired
    private GraphBuildService buildService;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void 测试插入一个公司Node() {
        companyRepository.deleteAll();
        Company company = new Company("123344444", "yfwefwe", "1", 2);
        Company savedRow = companyRepository.save(company);
        Assert.assertNotNull(savedRow);
    }

    @Test
    public void 测试创建一个关系图谱() {
        String entityId = "2327007317";//广东网金控股
        String _id = "a3fa592121da049a1041031c5ebb922e";
        buildService.createOneMap(_id,entityId);
    }


}
