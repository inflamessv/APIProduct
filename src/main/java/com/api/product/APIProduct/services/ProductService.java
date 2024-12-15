package com.api.product.APIProduct.services;

import com.api.product.APIProduct.entity.Product;
import com.api.product.APIProduct.repository.ProductRepo;
import com.api.product.APIProduct.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductI<Product,Long> {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepo repository;

    @Override
    public void add(Product entity) {
        if(entity == null){
            throw new IllegalArgumentException("The product entity must not be null");
        }
        /*Optional<Product>existinProduct=repository.findById(entity.getId());
        if (existinProduct.isPresent()){
            throw new ServiceException("Product already exist in db");
        }*/
        try {
            repository.save(entity);
            log.info("Product has been save successfully:{}",entity); ;
       }catch (RuntimeException e){
           log.info("Error saving product:{}",entity,e);
           throw new ServiceException("failed to save product",e);
       }
    }



    @Override
    public List<Product> getAll() {
        try{
            List<Product>products=repository.findAll();
            return products != null ? products : Collections.emptyList();
        }catch (RuntimeException e){
            log.error("Error retriving all products",e);
            throw new ServiceException("failed to fetch products",e);
        }

    }

    @Override
    public Product update(Long id, Product entity) {
        if(id == null || entity == null ){
            throw new IllegalArgumentException("Id or product must not be null");
        }
        try{
            Product existingProduct=repository.findById(id)
                    .orElseThrow(() -> new ServiceException("product with the id:"+id+"could not be found "+entity));

            existingProduct.setName(entity.getName());
            existingProduct.setPrice(entity.getPrice());
            existingProduct.setQuantity(entity.getQuantity());

            repository.save(existingProduct);
            log.info("product updated successfully");
            return existingProduct;
        }catch (RuntimeException e){
            log.info("Error updating product");
            throw new ServiceException("failed updating product");
        }
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id could not be null");
        }
        try{
            Product productDeleted=repository.findById(id)
                    .orElseThrow(() ->  new ServiceException("Product could not be found or doesn't exist"));
            repository.delete(productDeleted);
            log.info("product was deleted successfully");
        }catch (RuntimeException e){
            log.info("Error deleting product");
            throw new ServiceException("failed deleting product");
        }
    }

}
