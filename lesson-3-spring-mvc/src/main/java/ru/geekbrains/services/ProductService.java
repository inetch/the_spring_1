package ru.geekbrains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.model.Product;
import ru.geekbrains.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    private final Product emptyProduct;

    @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;
        this.emptyProduct = new Product();
    }

    public Product getEmptyProduct(){
        return emptyProduct;
    }

    public void remove(long id){
        Product product = repo.findById(id);
        repo.delete(product);
    }

    public void update(Product product){
        repo.update(product);
    }

    public List<Product> findAll(){
        return repo.findAll();
    }
    public List<Product> findAll(int pageNumber, int pageSize){
        return repo.findAll(pageNumber, pageSize);
    }

    public Product findById(long id){
        return repo.findById(id);
    }

    public List<Product> minPriceProducts(){
        return repo.getMinPriceProducts();
    }

    public List<Product> maxPriceProducts(){
        return repo.getMaxPriceProducts();
    }

    public List<Product> extremePriceProducts(){
        List<Product> list = repo.getMinPriceProducts();
        list.addAll(repo.getMaxPriceProducts());
        return list;
    }
}
