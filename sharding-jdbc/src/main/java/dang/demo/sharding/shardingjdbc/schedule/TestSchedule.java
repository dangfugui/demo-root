package dang.demo.sharding.shardingjdbc.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Slf4j
public class TestSchedule {

    @Autowired
    private DataSource dataSource;
    private static long index;

    //    @Scheduled(cron = "*/10 * * * * ?")
    @Async
    public void testLog() throws SQLException {
        log.info("test log index:{}", ++index);
        String sql = "INSERT INTO t_order0  SET order_id = ? ,order_name = ?,user_id = ?,create_time = now() ;";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, index);
            preparedStatement.setString(2, "order:" + index);
            preparedStatement.setLong(3, index);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    log.info(rs.toString());
                }
            }
        }
    }
}
