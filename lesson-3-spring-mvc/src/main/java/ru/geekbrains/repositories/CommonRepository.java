package ru.geekbrains.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

public abstract class CommonRepository<T> {

    private static final Logger logger = LoggerFactory.getLogger(CommonRepository.class);
    private final Map<String, Boolean> orderColumns = new HashMap<>();

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
    public void save(T entity) {
        persist(entity);
    }

    public void delete(T entity) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void addOrderColumn(String columnName, boolean isAsc){
        orderColumns.put(columnName, isAsc);
    }

    public void addAscColumn(String columnName){
        addOrderColumn(columnName, true);
    }

    public void addDescColumn(String columnName){
        addOrderColumn(columnName, false);
    }

    public void removeOrderColumn(String columnName){
        orderColumns.remove(columnName);
    }

    public String getQuery(Class<T> tClass){
        return String.format("select e from %s", tClass.getSimpleName());
    }

    public String getOrderedQuery(Class<T> tClass){
        if(orderColumns.isEmpty()){
            return getQuery(tClass);
        }

        StringBuilder order = new StringBuilder("order by");

        for (Map.Entry<String, Boolean> e : orderColumns.entrySet()) {
            order.append(String.format(" %s %s", e.getKey(), e.getValue() ? "asc" : "desc"));
        }

        return String.format("select e from %s %s", tClass.getSimpleName(), order.toString());
    }
}
