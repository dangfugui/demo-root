package dang.demo.sharding.shardingjdbc.module.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigDao extends JpaRepository<Config, Long> {
}
