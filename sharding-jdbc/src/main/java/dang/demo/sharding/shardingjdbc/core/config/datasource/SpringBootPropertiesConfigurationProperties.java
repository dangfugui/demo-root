package dang.demo.sharding.shardingjdbc.core.config.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

@ConfigurationProperties(prefix = "sharding.jdbc.config")
@Getter
@Setter
@Component
public class SpringBootPropertiesConfigurationProperties {

    private Properties props = new Properties();
}
