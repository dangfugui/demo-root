package dang.demo.sharding.shardingjdbc.module.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Long> {
}
