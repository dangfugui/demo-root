package dang.demo.sharding.shardingjdbc.core.config.datasource;

import io.shardingsphere.core.yaml.sharding.YamlShardingRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sharding.jdbc.config.sharding")
@Component
public class SpringBootShardingRuleConfigurationProperties extends YamlShardingRuleConfiguration {
}