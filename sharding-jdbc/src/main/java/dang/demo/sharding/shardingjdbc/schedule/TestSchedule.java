package dang.demo.sharding.shardingjdbc.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestSchedule {

    @Scheduled(cron = "*/30 * * * * ?")
    @Async
    public void testLog() {
        log.info("test log");
    }
}
