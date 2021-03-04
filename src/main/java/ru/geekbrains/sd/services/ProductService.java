package ru.geekbrains.sd.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.sd.model.Product;
import ru.geekbrains.sd.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAll(){
        return productRepo.findAll();
    }

    public Product getId(Long id){
        return productRepo.findById(id).get();
    }

    public Product add(Product product){
        return productRepo.save(product);
    }

    public void delete(Long id){
        productRepo.deleteById(id);
    }

    public List<Product> findLessThan(int max){
        return productRepo.findProductsByCostLessThan(max);
    }

    public List<Product> findGreaterThan(int min){
        return productRepo.findProductsByCostGreaterThan(min);
    }

    public List<Product>findBetween(int min, int max){
        return productRepo.findProductsByCostBetween(min,max);
    }

    public List<Product>findNameStart(String name){
        return productRepo.findProductsByNameStartsWith(name);
    }

}
