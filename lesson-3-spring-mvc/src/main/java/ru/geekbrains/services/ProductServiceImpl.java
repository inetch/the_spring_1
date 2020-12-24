package ru.geekbrains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.model.Product;
import ru.geekbrains.repositories.ProductRepository;
import ru.geekbrains.repositories.ProductRepositoryStandard;
import ru.geekbrains.repositories.ProductSpecification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
//    private final ProductRepository repo;
    private final ProductRepositoryStandard repo;

    @Value("5")
    private int pageSize;

    private final Product emptyProduct;

    @Autowired
    public ProductServiceImpl(ProductRepositoryStandard repo) {
        this.repo = repo;
        this.emptyProduct = new Product();
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Product getEmptyProduct(){
        return emptyProduct;
    }

    @Transactional
    public void remove(long id){
        repo.deleteById(id);
    }

    @Transactional
    public void update(Product product){
        repo.save(product);
    }

    public List<Product> findAll(){
        return repo.findAll();
    }
    public List<Product> findAll(int pageNumber) {
        return repo.findAll(pageNumber, pageSize);
    }

    @Override
    public Page<Product> findWithFilter(Optional<String> nameFilter,
                                        Optional<BigDecimal> minPrice,
                                        Optional<BigDecimal> maxPrice,
                                        Optional<Integer> page,
                                        Optional<String> sortField,
                                        Optional<String> sortOrder) {
        Specification<Product> spec = Specification.where(null);
//        if (nameFilter.isPresent()) {
//            spec = spec.and(ProductSpecification.nameLike(nameFilter.get()));
//        }
//        if (minPrice.isPresent()) {
//            spec = spec.and(ProductSpecification.minPriceFilter(minPrice.get()));
//        }
//        if (maxPrice.isPresent()) {
//            spec = spec.and(ProductSpecification.maxPriceFilter(maxPrice.get()));
//        }
//        if (sortField.isPresent() && sortOrder.isPresent()) {
//            return repo.findAll(spec, PageRequest.of(
//                    page.orElse(1) - 1, pageSize,
//                    Sort.by(Sort.Direction.fromString(sortOrder.get()), sortField.get()))
//            );
//        }
//        return repo.findAll(spec, PageRequest.of(page.orElse(1) - 1, pageSize));
        return null;
    }


    public Product findById(long id){
        return repo.findById(id);
    }

}
