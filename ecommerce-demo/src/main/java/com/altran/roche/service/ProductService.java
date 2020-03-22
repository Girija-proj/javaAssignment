package com.altran.roche.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altran.roche.model.Product;
import com.altran.roche.model.RocheProduct;
import com.altran.roche.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
 
    @Autowired
    private ProductRepository repo;
     
    public List<RocheProduct> getAllProducts() {  
        return repo.findAll();
    }
     
    public void saveProduct(Product product) {
    	RocheProduct rocheProduct = new RocheProduct(product.getName(),product.getPrice(),product.getQuantity());    	
        repo.save(rocheProduct);
    }
    
    public void updateProduct( String name, Double price, Integer quantity,  UUID sku, LocalDate date) {
    	RocheProduct rocheProduct = new RocheProduct(sku,name,price,quantity,date);    	
        repo.save(rocheProduct);
    }
     
    public RocheProduct findBySku(UUID id) {
        	
        return repo.findById(id).get();
    }
     
    public void deleteBySku(UUID id) {
    	RocheProduct product = findBySku(id);
    	product.setIsDelYN("Y");
    	repo.save(product); 
    }
}