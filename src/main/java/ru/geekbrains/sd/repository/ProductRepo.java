package ru.geekbrains.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.sd.model.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {


    List<Product> findProductsByCostLessThan(int max);
    List<Product> findProductsByCostGreaterThan(int min);
    List<Product>findProductsByCostBetween(int min, int max);
    List<Product> findProductsByNameStartsWith(String name);


}
