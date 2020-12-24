package ru.geekbrains.services;

import org.springframework.data.domain.Page;
import ru.geekbrains.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product getEmptyProduct();

    void remove(long id);
    void update(Product product);

    List<Product> findAll();
    List<Product> findAll(int pageNumber);

    Product findById(long id);
    Page<Product> findWithFilter(Optional<String> nameFilter,
                                 Optional<BigDecimal> minPrice,
                                 Optional<BigDecimal> maxPrice,
                                 Optional<Integer> page,
                                 Optional<String> sortField,
                                 Optional<String> sortOrder);
    void setPageSize(int pageSize);
    int getPageSize();
}
