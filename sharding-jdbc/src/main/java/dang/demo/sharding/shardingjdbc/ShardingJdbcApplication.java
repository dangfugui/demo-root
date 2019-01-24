package dang.demo.sharding.shardingjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "dang.demo.sharding.shardingjdbc",
        exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories("dang.demo.sharding.shardingjdbc")
@EntityScan("dang.demo.sharding.shardingjdbc")
//@EnableTransactionManagement(proxyTargetClass = true)   //开启事物管理功能
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }

}

