package ru.geekbrains.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class CommonRepository<T> {

    private static final Logger logger = LoggerFactory.getLogger(CommonRepository.class);

    @Autowired
    protected EntityManagerFactory emFactory;

    protected void persist(T entity) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void create(T entity){
        persist(entity);
    }

    public void update(T entity) {
        persist(entity);
    }

    public void delete(T entity) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

}
