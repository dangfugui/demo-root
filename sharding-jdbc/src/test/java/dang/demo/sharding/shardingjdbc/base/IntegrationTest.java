package dang.demo.sharding.shardingjdbc.base;

import dang.demo.sharding.shardingjdbc.ShardingJdbcApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * 集成测试父类 链接local 数据库
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcApplication.class)
@Transactional
public class IntegrationTest {


}
