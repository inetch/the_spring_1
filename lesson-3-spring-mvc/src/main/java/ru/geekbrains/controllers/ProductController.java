package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.model.Product;
import ru.geekbrains.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String indexProductPage(@RequestParam(required = false) String price, @RequestParam(required = false) Integer page, Model model) {
        logger.info("Product page update");
        List<Product> products;
        if(page == null){
            page = 1;
        }
        if (price != null) {
            switch (price) {
                case "min":
                    products = service.minPriceProducts();
                    break;
                case "max":
                    products = service.maxPriceProducts();
                    break;
                case "both":
                    products = service.extremePriceProducts();
                    break;
                default:
                    products = service.findAll(page, 5);
            }
        } else {
            products = service.findAll(page, 5);
        }

        model.addAttribute("products", products);

        return "product";
    }
/*
    @GetMapping
    public String indexProductPage(Model model) {
        logger.info("Product page update");
        List<Product> products;
        products = service.findAll();

        model.addAttribute("products", products);

        return "product";
    }*/

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {
        logger.info("Edit product with id {}", id);
        model.addAttribute("product", service.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        Product product = service.getEmptyProduct();
        logger.info("Add product");
        model.addAttribute("product", product);
        return "product_form";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        service.update(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id, Model model) {
        logger.info("Delete product with id {}", id);
        service.remove(id);
        return "redirect:/product";
    }
}
