package dang.demo.sharding.shardingjdbc.schedule;

import dang.demo.sharding.shardingjdbc.module.config.Config;
import dang.demo.sharding.shardingjdbc.module.config.ConfigDao;
import dang.demo.sharding.shardingjdbc.module.order.Order;
import dang.demo.sharding.shardingjdbc.module.order.OrderDao;
import dang.demo.sharding.shardingjdbc.module.order.OrderItem;
import dang.demo.sharding.shardingjdbc.module.order.OrderItemDao;
import dang.demo.sharding.shardingjdbc.module.user.User;
import dang.demo.sharding.shardingjdbc.module.user.UserDao;
import lombok.extern.slf4j.Slf4j;
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


    @Scheduled(cron = "*/10 * * * * ?")
    @Async
    @Transactional
    public void addToDB() {
        count++;
        User user = new User(null, "dang" + count);
        userDao.save(user);
        Config config = new Config(null, "config" + count, String.valueOf(count));
        configDao.save(config);
        Order order = new Order(null, user.getUserId(), "order" + config, new Date());
        orderDao.save(order);
        OrderItem orderItem = new OrderItem(null, order.getOrderId(), "orderItem" + count, 1.1, 1);
        orderItemDao.save(orderItem);
    }
}
