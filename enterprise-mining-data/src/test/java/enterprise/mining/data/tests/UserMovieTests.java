package enterprise.mining.data.tests;

import enterprise.mining.data.EnterpriseMiningDataApplication;
import enterprise.mining.data.model.Movie;
import enterprise.mining.data.model.User;
import enterprise.mining.data.model.Viewing;
import enterprise.mining.data.repositories.MovieRepository;
import enterprise.mining.data.repositories.UserRepository;
import enterprise.mining.data.repositories.ViewingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created by  yuananyun on 2017/8/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EnterpriseMiningDataApplication.class)
public class UserMovieTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ViewingRepository viewingRepository;


    @Test
    public void 测试操作用户实体() {
        User user = new User("john001", "John");
        User savedUser = userRepository.save(user);
        User loadedUser = userRepository.findOne(savedUser.getNodeId());
        Assert.assertTrue(Objects.equals(savedUser.getNodeId(), loadedUser.getNodeId()));

        User loadedUserViaIndex = userRepository.findByUserId("john001");

        Assert.assertTrue(savedUser.getUserId().equals(loadedUserViaIndex.getUserId()));
    }

    @Test
    public void 测试创建图谱() {

        userRepository.deleteAll();
        movieRepository.deleteAll();

        User user1 = new User("john001", "John Johnson");
        User user2 = new User("jack001", "Jack Jeffries");
        User user3 = new User("david001", "David Alpha");

        user1.setReferredBy(user3);
        Set<User> user1Friends = new HashSet<>();
        user1Friends.add(user2);
        user1.setFriends(user1Friends);

        userRepository.save(user1);
//        userRepository.save(user2);
        userRepository.save(user3);

        Movie movie1 = new Movie("Fargo");
        Movie movie2 = new Movie("Alien");
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Set<Viewing> user2Viewing = new HashSet<>();
        user2Viewing.add(new Viewing(user2, movie1, 4));
        user2Viewing.add(new Viewing(user2, movie2, 5));
        user2.setViews(user2Viewing);
        userRepository.save(user2);

        User savedUser2=userRepository.findByUserId(user2.getUserId());


    }


}
