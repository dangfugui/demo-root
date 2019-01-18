package dang.demo.sharding.shardingjdbc.module.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDao extends JpaRepository<OrderItem, Long> {
}
