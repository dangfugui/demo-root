package dang.demo.sharding.shardingjdbc.core.config.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "sharding.jdbc.datasource.ds1")
@Getter
@Setter
@Controller
public class Ds1MapConfig {
    private Map<String, Object> configMap = new LinkedHashMap<>();
}

