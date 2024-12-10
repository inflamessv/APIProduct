package com.api.product.APIProduct.Service;

import com.api.product.APIProduct.entity.Product;
import com.api.product.APIProduct.repository.ProductRepo;
import com.api.product.APIProduct.services.ProductService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepo repo;

    @InjectMocks
    private ProductService service;

    @Test
    public void testGetAllProducts(){
        List<Product> mock=List.of(
                new Product(1l,"test1",10.0,1),
                new Product(2l,"test2",10.0,1)
        );

        Mockito.when(repo.findAll()).thenReturn(mock);

        List<Product>products=service.getAll();

        Assertions.assertNotNull(products);
        Assertions.assertEquals(2,products.size());
        Assertions.assertEquals("test1",products.get(0).getName());
    }

    @Test
    public void testUpdateProduct(){
        Long id= 1L;
        Product existingProduct=new Product(1l,"update_Test1",10.0,10);
        Product updatedProduct=new Product(1l,"update_Test2",10.0,10);
        Mockito.when(repo.findById(id)).thenReturn(Optional.of(existingProduct));
        Mockito.when(repo.save(Mockito.any(Product.class))).thenReturn(existingProduct);

        service.update(id,updatedProduct);

        Assertions.assertEquals("update_Test2",existingProduct.getName());

    }

    @Test
    public void testDeletingProduct(){
        Product deletedProduct=new Product(1l,"test_prod",10.0,100);
        Mockito.when(repo.findById(deletedProduct.getId())).thenReturn(Optional.of(deletedProduct));
        service.delete(deletedProduct.getId());

        Mockito.verify(repo,Mockito.times(1)).findById(deletedProduct.getId());
        Mockito.verify(repo,Mockito.times(1)).delete(deletedProduct);
    }
}
