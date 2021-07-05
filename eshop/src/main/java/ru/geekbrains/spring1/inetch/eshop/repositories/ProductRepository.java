package ru.geekbrains.spring1.inetch.eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring1.inetch.eshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}