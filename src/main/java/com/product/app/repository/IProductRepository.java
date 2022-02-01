package com.product.app.repository;

import com.product.app.model.Product;
import com.product.app.model.TypeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(String category);
    List<Product> findByBrand(String brand);
    List<Product> findByType(String type);
//    //in case of projections use only derived quries
//    List<TypeView> findDistinctTypeByCategory(String category);
    @Query("select distinct category from Product")
    List<String> findDistinctByCategory();
}
