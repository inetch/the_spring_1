package ru.geekbrains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.model.Customer;
import ru.geekbrains.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    @Autowired
    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Customer create(String name){
        Customer customer = new Customer(null, name);
        repo.create(customer);
        return customer;
    }

    public void create(Customer customer){
        repo.create(customer);
    }

    public void remove(long id){
        Customer customer = repo.findById(id);
        repo.delete(customer);
    }

    public void updateName(long id, String name){
        Customer customer = repo.findById(id);
        customer.setName(name);
        repo.update(customer);
    }

    public void update(Customer customer){
        repo.update(customer);
    }

    public List<Customer> findAll(){
        return repo.findAll();
    }

    public Customer findById(long id){
        return repo.findById(id);
    }
}
