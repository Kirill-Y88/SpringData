package ru.geekbrains.sd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.sd.model.Product;
import ru.geekbrains.sd.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Product getById(@PathVariable Long id){
        return productService.getId(id);
    }

    @PostMapping
    public Product add(@RequestBody Product product){
        return productService.add(product);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        productService.delete(id);
        getAll();
    }

    @GetMapping("/findLess")
    public List<Product> findLessThan(@RequestParam int max){
        return productService.findLessThan(max);
    }

    @GetMapping("/findGreater")
    public List<Product> findGreaterThan(@RequestParam int min){
        return productService.findGreaterThan(min);
    }

    @GetMapping("/findBetween")
    public List<Product> findBetween(@RequestParam int min, @RequestParam int max){
        return productService.findBetween(min, max);
    }

    @GetMapping("/findName")
    public List<Product> findName(@RequestParam String name){
        return productService.findNameStart(name);
    }

}
