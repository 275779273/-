package com.itcast.service;

import com.itcast.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);
}
