package dang.demo.sharding.shardingjdbc.module.user;


import dang.demo.sharding.shardingjdbc.base.IntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends IntegrationTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void add() {
        User user = new User();
        user.setUserName("dang");
        user = userDao.save(user);
        Long userId = user.getUserId();
        Assert.assertEquals("dang", userDao.getOne(user.getUserId()).getUserName());
        userDao.delete(userId);
        Assert.assertNull(userDao.findOne(userId));
    }

}