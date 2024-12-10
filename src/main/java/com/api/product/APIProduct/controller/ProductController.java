package com.api.product.APIProduct.controller;

import com.api.product.APIProduct.entity.Product;
import com.api.product.APIProduct.exceptions.ServiceException;
import com.api.product.APIProduct.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>>getAllProducts(){
        try {
            return ResponseEntity.ok(service.getAll());
        }catch(ServiceException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.EMPTY_LIST);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String>addProduct(@RequestBody Product prod){
        try{
            service.add(prod);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product");
        }
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?>updateProduct(@PathVariable Long id,@RequestBody Product prod){
        try{
            Product updatedProduct=service.update(id,prod);
            return ResponseEntity.ok(updatedProduct);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product");
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?>deleteProduct(@PathVariable Long id){
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product");
        }
    }

}
