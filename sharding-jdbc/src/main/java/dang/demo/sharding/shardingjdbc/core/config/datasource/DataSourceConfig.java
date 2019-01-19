package dang.demo.sharding.shardingjdbc.core.config.datasource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import io.shardingsphere.shardingjdbc.util.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://www.jianshu.com/p/639253764705
 */
@Configuration
public class DataSourceConfig {
    @Autowired
    Ds0MapConfig ds0MapConfig;
    @Autowired
    Ds1MapConfig ds1MapConfig;
    @Autowired
    SpringBootPropertiesConfigurationProperties springBootShardingRuleConfigurationProperties;

    //    @Bean
    DataSource getShardingDataSource() throws SQLException, ReflectiveOperationException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
//        shardingRuleConfig.getBroadcastTables().add("t_config");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(
                new StandardShardingStrategyConfiguration("order_id", new TablePreciseShardingAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig,
                ds0MapConfig.getConfigMap(), springBootShardingRuleConfigurationProperties.getProps());
    }

    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order");
        result.setActualDataNodes("ds${0..1}.t_order${0..1}");
        result.setKeyGeneratorColumnName("order_id");
        return result;
    }

    TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order_item");
        result.setActualDataNodes("ds${0..1}.t_order_item${0..1}");
        return result;
    }

    Map<String, DataSource> createDataSourceMap() throws ReflectiveOperationException {
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds0", DataSourceUtil.getDataSource("ds0", ds0MapConfig.getConfigMap()));
        result.put("ds1", DataSourceUtil.getDataSource("ds1", ds1MapConfig.getConfigMap()));
        return result;
    }

}