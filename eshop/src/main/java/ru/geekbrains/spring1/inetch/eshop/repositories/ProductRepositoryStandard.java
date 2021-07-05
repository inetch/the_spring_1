package ru.geekbrains.spring1.inetch.eshop.repositories;

import org.springframework.stereotype.Repository;
import ru.geekbrains.spring1.inetch.eshop.model.Product;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductRepositoryStandard extends CommonRepository<Product>{
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

    public void deleteById(Long id){
        delete(findById(id));
    }
}
