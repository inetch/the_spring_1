package ru.geekbrains.repositories;

import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Product;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductRepository extends CommonRepository<Product>{
    public Product findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.createQuery("select p from Product p where id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();

        return product;
    }

    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> products = em.createQuery("select p from Product p", Product.class)
                .getResultList();
        em.close();
        return products;
    }

    public List<Product> findAll(int pageNumber, int pageSize) {
        EntityManager em = emFactory.createEntityManager();
        List<Product> products = em.createQuery("select p from Product p", Product.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        em.close();
        return products;
    }

    public List<Product> getMinPriceProducts() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> products = em.createQuery("select p from Product p where price = (select min(price) from Product)", Product.class)
                .getResultList();
        em.close();
        return products;
    }

    public List<Product> getMaxPriceProducts() {
        EntityManager em = emFactory.createEntityManager();
        List<Product> products = em.createQuery("select p from Product p where price = (select max(price) from Product)", Product.class)
                .getResultList();
        em.close();
        return products;
    }
}
