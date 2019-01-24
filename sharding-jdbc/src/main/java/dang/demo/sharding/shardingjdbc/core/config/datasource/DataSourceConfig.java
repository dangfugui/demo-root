package dang.demo.sharding.shardingjdbc.core.config.datasource;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


//    @Bean(autowire = Autowire.BY_NAME, name = "ds0DataSource")
//    @Qualifier("ds0DataSource")
//    @ConfigurationProperties(prefix = "sharding.jdbc.datasource.ds0")
//    public DataSource ds0DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(autowire = Autowire.BY_NAME, name = "ds1DataSource")
//    @Qualifier("ds1DataSource")
//    @ConfigurationProperties(prefix = "sharding.jdbc.datasource.ds1")
//    public DataSource ds1DataSource() {
//        return DataSourceBuilder.create().build();
//    }
}
