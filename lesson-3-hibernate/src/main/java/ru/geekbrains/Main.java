package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // INSERT
//        EntityManager em = emFactory.createEntityManager();
//
//        em.getTransaction().begin();
//        Product product = new Product(null, "Some product 1", "Some description 1", new BigDecimal(14));
//        Product product1 = new Product(null, "iPad", "Super table", new BigDecimal(1400));
//        Product product2 = new Product(null, "iPhone", "Super mobile phone", new BigDecimal(900));
//        em.persist(product);
//        em.persist(product1);
//        em.persist(product2);
//        em.getTransaction().commit();
//
//        em.close();

        // SELECT
//        EntityManager em = emFactory.createEntityManager();
//
//        Product product = em.find(Product.class, 1L);
//        System.out.println(product);
//
//        // HQL, JPQL
//        List<Product> products = em.createQuery("from Product", Product.class)
//                .getResultList();
//        products.forEach(System.out::println);
//
//        List<Product> products1 = em.createQuery("from Product p where p.name = :name ", Product.class)
//                .setParameter("name", "iPad")
//                .getResultList();
//        products1.forEach(System.out::println);

        // SQL
//        em.createNativeQuery("select * from products", Product.class)
//                .getResultList();
//        products.forEach(System.out::println);

//        em.close();

        // UPDATE
//        EntityManager em = emFactory.createEntityManager();
//
//        Product product = em.find(Product.class, 1L);
//        System.out.println(product);
//
//        em.getTransaction().begin();
//        product.setName("Macbook pro");
//        em.getTransaction().commit();
//
//        em.close();

        // DELETE
//        EntityManager em = emFactory.createEntityManager();
//
//        em.getTransaction().begin();
//        Product product = em.find(Product.class, 1L);
//        em.remove(product);
//        em.getTransaction().commit();
//
//        List<Product> products = em.createQuery("from Product", Product.class)
//                .getResultList();
//        products.forEach(System.out::println);
//
//        em.close();

        // INSERT one-to-many
//        EntityManager em = emFactory.createEntityManager();
//
//        em.getTransaction().begin();
//
//        em.persist(new Category(null, "Laptop"));
//        em.persist(new Category(null, "Phone"));
//        em.persist(new Category(null, "Tablet"));
//
//        Category laptop = em.createNamedQuery("findByName", Category.class)
//                .setParameter("name", "Laptop")
//                .getSingleResult();
//        Category phone = em.createNamedQuery("findByName", Category.class)
//                .setParameter("name", "Phone")
//                .getSingleResult();
//        Category tablet = em.createNamedQuery("findByName", Category.class)
//                .setParameter("name", "Tablet")
//                .getSingleResult();
//
//        em.persist(new Product(null, "Macbook pro", "Super laptop", new BigDecimal(3000), laptop));
//        em.persist(new Product(null, "iPad", "Super tablet", new BigDecimal(3000), tablet));
//        em.persist(new Product(null, "iPhone", "Super phone", new BigDecimal(3000), phone));
//
//        em.getTransaction().commit();
//
//        em.close();

        // SELECT one-to-many
        EntityManager em = emFactory.createEntityManager();

        List<Product> products = em.createQuery("select p from Product p inner join p.category c", Product.class)
                .getResultList();
        products.forEach(System.out::println);
    }
}
