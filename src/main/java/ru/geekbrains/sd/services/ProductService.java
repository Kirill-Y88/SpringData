package ru.geekbrains.sd.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.sd.model.Product;
import ru.geekbrains.sd.model.SortDir;
import ru.geekbrains.sd.repository.ProductRepo;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public List<Product> getAll(int page, int size, SortDir sortName, SortDir sortCost){

        List<Product> products = null;
            if(sortName == null && sortCost == null){
                products = productRepo.findAll(PageRequest.of(page,size, Sort.by("cost").ascending())).toList();
        }
            if(sortName != null && sortCost == null){
                if (sortName == SortDir.ASC){
                    products = productRepo.findAll(PageRequest.of(page,size, Sort.by("name").ascending())).toList();
                }else {
                    products = productRepo.findAll(PageRequest.of(page,size, Sort.by("name").descending())).toList();
                }
            }
            if(sortName == null && sortCost != null){
                if (sortCost == SortDir.ASC){
                    products = productRepo.findAll(PageRequest.of(page,size, Sort.by("cost").ascending())).toList();
                }else {
                    products = productRepo.findAll(PageRequest.of(page,size, Sort.by("cost").descending())).toList();
                }
            }
            if(sortName != null && sortCost != null){
                if(sortName == SortDir.ASC && sortCost == SortDir.ASC){
                    products = productRepo.findAll(PageRequest.of(page,size, Sort.by("cost").ascending().and(Sort.by("name").ascending()))).toList();
                }
                if(sortName == SortDir.DESC && sortCost == SortDir.ASC){
                    products = productRepo.findAll(PageRequest.of(page,size, Sort.by("cost").ascending().and(Sort.by("name").descending()))).toList();
                }
                if(sortName == SortDir.ASC && sortCost == SortDir.DESC){
                    products = productRepo.findAll(PageRequest.of(page,size, Sort.by("cost").descending().and(Sort.by("name").ascending()))).toList();
                }
                if(sortName == SortDir.DESC && sortCost == SortDir.DESC){
                    products = productRepo.findAll(PageRequest.of(page,size, Sort.by("cost").descending().and(Sort.by("name").descending()))).toList();
                }
            }
            return products;
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
