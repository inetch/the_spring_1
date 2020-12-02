package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Main {

    private static void initSomeProducts(EntityManagerFactory emFactory){
        EntityManager em = emFactory.createEntityManager();

        Category laptop = em.createNamedQuery("findByName", Category.class)
                .setParameter("name", "Laptop")
                .getSingleResult();
        Category phone = em.createNamedQuery("findByName", Category.class)
                .setParameter("name", "Phone")
                .getSingleResult();
        Category tablet = em.createNamedQuery("findByName", Category.class)
                .setParameter("name", "Tablet")
                .getSingleResult();

        em.getTransaction().begin();

        em.persist(new Product(null, "IBM", "Some description 1", new BigDecimal(2400), laptop));
        em.persist(new Product(null, "iPad", "Super table", new BigDecimal(1400), tablet));
        em.persist(new Product(null, "iPhone", "Super mobile phone", new BigDecimal(900), phone));
        em.getTransaction().commit();

        em.close();
    }

    public static void initCategories(EntityManagerFactory emFactory){
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(new Category(null, "Laptop"));
        em.persist(new Category(null, "Phone"));
        em.persist(new Category(null, "Tablet"));

        em.getTransaction().commit();

        em.close();
    }

    public static void initCustomers(EntityManagerFactory emFactory){
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(new Customer(null, "Homer"));
        em.persist(new Customer(null, "Bart"));
        em.persist(new Customer(null, "Maggie"));

        em.getTransaction().commit();

        em.close();
    }

    public static void initOrders(EntityManagerFactory emFactory){
        EntityManager em = emFactory.createEntityManager();

        Product ibm = em.createNamedQuery("findByName", Product.class)
                .setParameter("name", "IBM")
                .getSingleResult();
        Product ipad = em.createNamedQuery("findByName", Product.class)
                .setParameter("name", "iPad")
                .getSingleResult();
        Product iphone = em.createNamedQuery("findByName", Product.class)
                .setParameter("name", "iPhone")
                .getSingleResult();

        Customer homer = em.createNamedQuery("findByName", Customer.class)
                .setParameter("name", "Homer")
                .getSingleResult();
        Customer bart = em.createNamedQuery("findByName", Customer.class)
                .setParameter("name", "Bart")
                .getSingleResult();
        Customer maggie = em.createNamedQuery("findByName", Customer.class)
                .setParameter("name", "maggie")
                .getSingleResult();

        em.getTransaction().begin();
        Order order = new Order(null, null, homer);
        em.persist(order);
        em.persist(new OrderItem(null, null, ibm, order, new BigDecimal(2)));
        em.persist(new OrderItem(null, null, iphone, order, new BigDecimal(5)));
        em.getTransaction().commit();

        em.getTransaction().begin();
        order = new Order(null, null, bart);
        em.persist(order);
        em.persist(new OrderItem(null, null, ipad, order, new BigDecimal(1)));
        em.getTransaction().commit();

        em.getTransaction().begin();
        order = new Order(null, null, homer);
        em.persist(order);
        em.persist(new OrderItem(null, null, iphone, order, new BigDecimal(30)));
        em.getTransaction().commit();

        em.getTransaction().begin();
        order = new Order(null, null, maggie);
        order.addItem(new OrderItem(null, null, ipad, order, new BigDecimal(1)));
        order.addItem(new OrderItem(null, null, iphone, order, new BigDecimal(11)));
        em.persist(order);
//        em.persist(new OrderItem(null, null, iphone, order, new BigDecimal(30)));
        em.getTransaction().commit();

        em.close();
    }

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
