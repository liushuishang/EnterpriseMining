package enterprise.mining.data.tests;

import com.alibaba.fastjson.JSON;
import enterprise.mining.data.EnterpriseMiningDataApplication;
import enterprise.mining.data.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * Created by  yuananyun on 2017/7/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnterpriseMiningDataApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskTest {

    @LocalServerPort
    private int port;
    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void findByTaskName() {
        ResponseEntity<Task> test = this.restTemplate.getForEntity(
                this.base.toString() + "/测试任务", Task.class);
        System.out.println(JSON.toJSONString(test.getBody()));
    }


    @Test
    public void saveTask() {
        Task task = new Task();
        task.setTaskName("测试任务");
        ResponseEntity<Task> test = this.restTemplate.postForEntity(
                this.base.toString() + "/task", task, Task.class);
        System.out.println(JSON.toJSONString(test.getBody()));
    }
}
