package com.product.app.controllers;

import com.product.app.model.Product;
import com.product.app.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("product-api")
public class controller {
    private IProductService productService;

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAll() {
         return productService.getAll();
    }

    @GetMapping("/products/brand/{brand}")
    public List<Product> getByBrand(@PathVariable("brand") String brand) {
         return productService.getByBrand(brand);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getByCategory(@PathVariable("category") String category) {
        return productService.getByCategory(category);
    }
    @GetMapping("/products/type/{type}")
    public List<Product> getByType(@PathVariable("type") String type) {
        return productService.getByType(type);
    }
//    @GetMapping("/products/categorytype/{category}")
//    public List<TypeView> getTypeByCategory(@PathVariable("category") String category) {
//        return productService.getByTypeCategory(category);
//    }

    @GetMapping("/products/category/distinct")
    public List<String> getDistinctCategory() {
        return productService.getDistinctCategory();
    }
    @GetMapping("/products/id/{productId}")
    public Product getById(@PathVariable("productId") int productId) {
        return productService.getById(productId);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
    }
}
