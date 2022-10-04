package com.example.crud_product_with_list.service;

import com.example.crud_product_with_list.model.Product;

import java.util.ArrayList;

public interface IProductService {
    ArrayList<Product> findAll();

    Product findProductById(int id);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int id);

    void updateProductsByName(String name);

    void change(int id, Long price);
}
