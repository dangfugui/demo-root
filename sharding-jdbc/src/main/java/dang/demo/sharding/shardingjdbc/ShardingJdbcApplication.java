package dang.demo.sharding.shardingjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "dang.demo.sharding.shardingjdbc")
@EnableJpaRepositories("dang.demo.sharding.shardingjdbc")
@EntityScan("dang.demo.sharding.shardingjdbc")
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }

}

