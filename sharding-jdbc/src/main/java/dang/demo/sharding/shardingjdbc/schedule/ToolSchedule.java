package dang.demo.sharding.shardingjdbc.schedule;

import dang.demo.sharding.shardingjdbc.core.dao.JpaManager;
import dang.demo.sharding.shardingjdbc.core.unit.Skill;
import dang.demo.sharding.shardingjdbc.module.config.Config;
import dang.demo.sharding.shardingjdbc.module.config.ConfigDao;
import dang.demo.sharding.shardingjdbc.module.order.Order;
import dang.demo.sharding.shardingjdbc.module.order.OrderDao;
import dang.demo.sharding.shardingjdbc.module.order.OrderItem;
import dang.demo.sharding.shardingjdbc.module.order.OrderItemDao;
import dang.demo.sharding.shardingjdbc.module.user.User;
import dang.demo.sharding.shardingjdbc.module.user.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
@Slf4j
public class ToolSchedule {

    private static long count = 0;

    @Autowired
    private UserDao userDao;
    @Autowired
    private ConfigDao configDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private JpaManager jpaManager;

    private User user;
    private Config config;
    private Order order;
    private OrderItem orderItem;


    @Scheduled(cron = "*/10 * * * * ?")
    @Async
    @Transactional
    public void addToDB() {
        SQLQuery query = jpaManager.getSession().createSQLQuery("SELECT 1 FROM DUAL");
        count++;
        user = new User(count, "dang" + count);
        Skill.tryDo(() -> userDao.save(user));
        config = new Config(null, "config" + count, String.valueOf(count));
        Skill.tryDo(() -> configDao.save(config));
        order = new Order(null, user.getUserId(), "order" + config, new Date());
        Skill.tryDo(() -> orderDao.save(order));
        orderItem = new OrderItem(null, order.getOrderId(), "orderItem" + count, 1.1, 1);
        Skill.tryDo(() -> orderItemDao.save(orderItem));
    }


    //    @Scheduled(cron = "*/10 * * * * ?")
    @Async
    @Transactional
    public void test() {
        count++;
        order = new Order(null, 1L, "order" + config, new Date());
        Skill.tryDo(() -> orderDao.save(order));
        orderItem = new OrderItem(null, 1L, "orderItem" + count, 1.1, 1);
        Skill.tryDo(() -> orderItemDao.save(orderItem));
    }
}
