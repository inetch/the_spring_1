package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.model.Product;
import ru.geekbrains.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
    public String indexProductPage(@RequestParam(name = "nameFilter") Optional<String>     nameFilter,
                                   @RequestParam(name = "minPrice")   Optional<BigDecimal> minPrice,
                                   @RequestParam(name = "maxPrice")   Optional<BigDecimal> maxPrice,
                                   @RequestParam(name = "page")       Optional<Integer>    page,
                                   @RequestParam(name = "size")       Optional<Integer>    size,
                                   @RequestParam(name = "sortField")  Optional<String>     sortField,
                                   @RequestParam(name = "sortOrder")  Optional<String>     sortOrder,
                                   Model model) {
        size.ifPresent(service::setPageSize);
        List<Product> products;

        if(!page.isEmpty()){
            products = service.findAll(page.get());
        }else{
            products = service.findAll();
        }

        //List<Product> products = service.findWithFilter(nameFilter, minPrice, maxPrice, page, sortField, sortOrder).toList();
        model.addAttribute("products", products);

        return "product";
    }

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
