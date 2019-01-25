package dang.demo.sharding.shardingjdbc.core.config.datasource;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShardingDataSourceConfig {

    @Autowired
    Environment environment;

    @Autowired
    SpringBootPropertiesConfigurationProperties shardingProps;
    @Autowired
    ConfigMap configMap;

//    @Resource(name = "ds0DataSource")
//    private DataSource ds0DataSource;
//    @Resource(name = "ds1DataSource")
//    private DataSource ds1DataSource;

    @Primary
    @Bean
    public DataSource getDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getBroadcastTables().add("t_config");
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new TablePreciseShardingAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig,
                new LinkedHashMap<>(), shardingProps.getProps());
    }

    private TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_user");
        result.setActualDataNodes("ds${0..1}.t_user");
        result.setKeyGeneratorColumnName("user_id");
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration(
                "user_id", "ds${user_id % 2}"));
        return result;
    }

    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        // 配置Order表规则
        result.setLogicTable("t_order");
        result.setActualDataNodes("ds${0..1}.t_order${0..1}");
//        result.setKeyGeneratorColumnName("order_id");
        // 配置分库 + 分表策略
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration(
                "user_id", "ds${user_id % 2}"));
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration(
                "order_id", "t_order${order_id % 2}"));
        return result;
    }

    TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order_item");
        result.setActualDataNodes("ds${0..1}.t_order_item${0..1}");
//        result.setKeyGeneratorColumnName("order_item_id");
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration(
                "user_id", "ds${user_id % 2}"));
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration(
                "order_id", "t_order_item${order_id % 2}"));
        return result;
    }


    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        DriverManagerDataSource dataSource0 = new DriverManagerDataSource();
        dataSource0.setDriverClassName(environment.getProperty("sharding.jdbc.datasource.ds0.driver-class-name"));
        dataSource0.setUrl(environment.getProperty("sharding.jdbc.datasource.ds0.url"));
        dataSource0.setUsername(environment.getProperty("sharding.jdbc.datasource.ds0.username"));
        dataSource0.setPassword(environment.getProperty("sharding.jdbc.datasource.ds0.password"));
        result.put("ds0", dataSource0);
        DriverManagerDataSource dataSource1 = new DriverManagerDataSource();
        dataSource1.setDriverClassName(environment.getProperty("sharding.jdbc.datasource.ds1.driver-class-name"));
        dataSource1.setUrl(environment.getProperty("sharding.jdbc.datasource.ds1.url"));
        dataSource1.setUsername(environment.getProperty("sharding.jdbc.datasource.ds1.username"));
        dataSource1.setPassword(environment.getProperty("sharding.jdbc.datasource.ds1.password"));
        result.put("ds1", dataSource1);
        return result;
    }


}
