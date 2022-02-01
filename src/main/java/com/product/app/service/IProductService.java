package com.product.app.service;

import com.product.app.model.Product;


import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    List<Product> getAll();
    List<Product> getByBrand(String brand);
    List<Product> getByCategory(String category);
    List<Product> getByType(String type);
//    List<TypeView> getByTypeCategory(String category);
    List<String> getDistinctCategory();


    Product getById(int productId);
    void updateProduct(Product product);
    void deleteProduct(int productId);
}
