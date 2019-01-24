package dang.demo.sharding.shardingjdbc.core.config.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "sharding.jdbc.config")
@Data
@Controller
public class ConfigMap {
    private Map<String, Object> sharding = new LinkedHashMap<>();
}

