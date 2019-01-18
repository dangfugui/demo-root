package dang.demo.sharding.shardingjdbc.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置类.
 */
@EnableScheduling
@EnableAsync
@ConditionalOnProperty(value = "scheduling.enable", havingValue = "true", matchIfMissing = true)
@Configuration
public class ScheduleConfig {
}
