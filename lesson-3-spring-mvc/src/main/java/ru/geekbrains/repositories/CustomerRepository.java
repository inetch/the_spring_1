package ru.geekbrains.repositories;

import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Customer;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerRepository extends CommonRepository<Customer>{
    public Customer findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        Customer customer = em.createQuery("select c from Customer c where id = :id", Customer.class)
                .setParameter("id", id)
                .getSingleResult();

        em.close();
        return customer;
    }

    public List<Customer> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<Customer> customers = em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
        em.close();
        return customers;
    }
}
