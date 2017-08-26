//package enterprise.mining.data.tests;
//
//import enterprise.mining.data.EnterpriseMiningDataApplication;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//import java.util.Map;
//
///**
// * Created by  yuananyun on 2017/8/26.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = EnterpriseMiningDataApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class MongodbTest {
//
//    @Autowired
//    private MongoTemplate template;
//
//    @Test
//    public void 测试数据库连接正常() {
//        Map obj = template.findById("99483179dd38712556798172c823dbfd", Map.class, "map_info");
//        Assert.assertNotNull(obj);
//    }
//
//
//}
