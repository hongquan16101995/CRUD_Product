package com.example.crud_product_with_list.service;

import com.example.product.servlet_jsp.model.Product;

import java.util.ArrayList;

public class ProductServiceImpl implements IProductService {
    private final ArrayList<Product> products = new ArrayList<>();

    @Override
    public ArrayList<Product> findAll() {
        return products;
    }

    @Override
    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                break;
            }
        }
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }
}
