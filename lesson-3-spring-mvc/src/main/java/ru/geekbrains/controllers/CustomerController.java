package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.model.Customer;
import ru.geekbrains.services.CustomerServiceImpl;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerServiceImpl service;

    @Autowired
    public CustomerController(CustomerServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String indexCustomerPage(Model model) {
        logger.info("Customer page update");
        model.addAttribute("customers", service.findAll());
        return "customer";
    }

    @GetMapping("/{id}")
    public String editCustomer(@PathVariable(value = "id") Long id, Model model) {
        logger.info("Edit customer with id {}", id);
        model.addAttribute("customer", service.findById(id));
        return "customer_form";
    }

    @GetMapping("/new")
    public String newCustomer(Model model) {
        Customer customer = new Customer();
        logger.info("Add new customer");
        model.addAttribute("customer", customer);
        return "customer_form";
    }

    @PostMapping("/update") //customer_form.html, button submit
    public String updateCustomer(Customer customer) {
        service.update(customer);
        return "redirect:/customer";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Long id, Model model) {
        logger.info("Delete customer with id {}", id);
        service.remove(id);
        return "redirect:/customer";
    }
}
