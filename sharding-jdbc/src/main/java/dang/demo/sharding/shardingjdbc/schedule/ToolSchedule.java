package dang.demo.sharding.shardingjdbc.schedule;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;

@Component
@Slf4j
public class ToolSchedule implements ApplicationRunner {

    private static long count = 1;

    @Autowired
    private UserDao userDao;
    @Autowired
    private ConfigDao configDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @PersistenceContext
    private EntityManager entityManager;

    long orderCount = 2;
    long orderItemCount = 2;


    //    @Scheduled(cron = "*/5 * * * * ?")
    @Scheduled(fixedDelay = 2000)
    @Async
    @Transactional
    public void addToDB() {
        log.info("addToDB count:{}", ++count);
//        Config config = new Config(count, "config" + count, String.valueOf(count));
//        Skill.tryDo(() -> configDao.save(config));
        User user = new User(count, "dang" + count);
        Skill.tryDo(() -> userDao.save(user));
        for (int o = 0; o < orderCount; o++) {
            long orderId = count * orderCount + o;
            Order order = new Order(orderId, count, "order:" + orderId, new Date());
            Skill.tryDo(() -> orderDao.save(order));
            for (int i = 0; i < orderItemCount; i++) {
                long orderItemId = orderId * orderItemCount + i;
                OrderItem orderItem = new OrderItem(orderItemId, orderId, "orderItem:" + orderItemId, 1.1, count);
                Skill.tryDo(() -> orderItemDao.save(orderItem));
            }
        }
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Query query = entityManager.createNativeQuery("select max(user_id) from t_user;");
        BigInteger max = (BigInteger) query.getResultList().get(0);
        count = max == null ? 0 : max.longValue();
    }
}
