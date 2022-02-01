package com.product.app.service;

import com.product.app.model.Product;
import com.product.app.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    private IProductRepository productRepository;

    @Autowired
    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getByType(String type) {
        return productRepository.findByType(type);
    }

//    @Override
//    public List<TypeView> getByTypeCategory(String category) {
//        return productRepository.findDistinctTypeByCategory(category);
//    }

    @Override
    public List<String> getDistinctCategory() {
        return productRepository.findDistinctByCategory();
    }

    @Override
    public Product getById(int productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }
}
