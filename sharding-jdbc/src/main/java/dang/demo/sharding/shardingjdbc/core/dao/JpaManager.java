package dang.demo.sharding.shardingjdbc.core.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Slf4j
public class JpaManager {
    private EntityManager entityManager = null;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Session getSession() {
        HibernateEntityManager hEntityManager = (HibernateEntityManager) entityManager;
        Session session = hEntityManager.getSession();
        return session;
    }
}
