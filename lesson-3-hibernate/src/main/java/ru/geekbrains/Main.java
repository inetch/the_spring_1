package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static void initSomeProducts(EntityManagerFactory emFactory){
        EntityManager em = emFactory.createEntityManager();

        Category laptop = em.createNamedQuery("categoryByName", Category.class)
                .setParameter("name", "Laptop")
                .getSingleResult();
        Category phone = em.createNamedQuery("categoryByName", Category.class)
                .setParameter("name", "Phone")
                .getSingleResult();
        Category tablet = em.createNamedQuery("categoryByName", Category.class)
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

        Product ibm = em.createNamedQuery("productByName", Product.class)
                .setParameter("name", "IBM")
                .getSingleResult();
        Product ipad = em.createNamedQuery("productByName", Product.class)
                .setParameter("name", "iPad")
                .getSingleResult();
        Product iphone = em.createNamedQuery("productByName", Product.class)
                .setParameter("name", "iPhone")
                .getSingleResult();

        Customer homer = em.createNamedQuery("customerByName", Customer.class)
                .setParameter("name", "Homer")
                .getSingleResult();
        Customer bart = em.createNamedQuery("customerByName", Customer.class)
                .setParameter("name", "Bart")
                .getSingleResult();
        Customer maggie = em.createNamedQuery("customerByName", Customer.class)
                .setParameter("name", "Maggie")
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
        em.persist(order);
        em.persist(new OrderItem(null, null, ipad, order, new BigDecimal(1)));
        em.persist(new OrderItem(null, null, iphone, order, new BigDecimal(11)));
//        em.persist(new OrderItem(null, null, iphone, order, new BigDecimal(30)));
        em.getTransaction().commit();

        em.close();
    }

    private static List<Product> getCustomerProducts(EntityManager em, String customerName){
        return em.createQuery("select distinct p\n" +
                "          from Customer c\n" +
                "            inner join c.orders as o\n" +
                "            inner join o.items as i\n" +
                "            inner join i.product as p\n" +
                "         where c.name = :name", Product.class)
                .setParameter("name", customerName)
                .getResultList();
    }

    private static List<Customer> getProductBuyers(EntityManager em, String productName){
        return em.createQuery("select distinct c\n" +
                "          from Customer c\n" +
                "            inner join c.orders as o\n" +
                "            inner join o.items as i\n" +
                "            inner join i.product as p\n" +
                "         where p.name = :name", Customer.class)
                .setParameter("name", productName)
                .getResultList();
    }

    private static void removeProduct(EntityManager em, String productName){
        em.getTransaction().begin();
        Product product = em.createNamedQuery("productByName", Product.class)
                .setParameter("name", productName)
                .getSingleResult();
        em.remove(product);
        em.getTransaction().commit();
    }

    private static void removeCustomer(EntityManager em, String customerName){
        em.getTransaction().begin();
        Customer customer = em.createNamedQuery("productByName", Customer.class)
                .setParameter("name", customerName)
                .getSingleResult();
        em.remove(customer);
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

//        initCategories(emFactory);
//        initCustomers(emFactory);
//        initSomeProducts(emFactory);
//        initOrders(emFactory);

        EntityManager em = emFactory.createEntityManager();

        //все продукты, что покупал Гомер
        List<Product> products = getCustomerProducts(em, "Homer");
        products.forEach(System.out::println);

        //все, кто покупал iPhone
        List<Customer> customers = getProductBuyers(em, "iPhone");
        customers.forEach(System.out::println);

        // удалим iPhone
        removeProduct(em, "iPhone");
        //только удаление не пройдёт, тк на внешних ключах нет опции cascade

        em.close();

    }
}
