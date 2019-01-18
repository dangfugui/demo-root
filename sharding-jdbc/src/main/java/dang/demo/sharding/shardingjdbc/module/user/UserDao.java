package dang.demo.sharding.shardingjdbc.module.user;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends JpaRepository<User, Long> {
}
