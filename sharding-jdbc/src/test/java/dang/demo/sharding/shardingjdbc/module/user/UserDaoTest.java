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
        Long userId = user.getId();
        Assert.assertEquals("dang", userDao.getOne(user.getId()).getUserName());
        userDao.deleteById(userId);
        Assert.assertFalse(userDao.findById(userId).isPresent());
    }

}