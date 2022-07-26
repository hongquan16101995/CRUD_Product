package com.example.crud_product_with_list.service;

import com.example.crud_product_with_list.DAO.ProductRepository;
import com.example.crud_product_with_list.model.Product;

import java.util.ArrayList;

public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl() {
        productRepository = new ProductRepository();
    }

    @Override
    public ArrayList<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.updateProductById(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteProductById(id);
    }
}
