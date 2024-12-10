package com.api.product.APIProduct.services;

import java.util.List;

public interface ProductI <T,ID>{

    void add(T entity);
    List<T>getAll();
    T update(ID id,T entity);
    void delete(ID id);

}
