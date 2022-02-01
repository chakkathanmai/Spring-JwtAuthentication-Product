package com.product.app;

import com.product.app.model.Product;
import com.product.app.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProductAppJwtApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringProductAppJwtApplication.class, args);
	}

	@Autowired
	private IProductService productService;

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product();
        product.setName("Helix Time Watch");
		product.setPrice(2000);
		product.setBrand("puma");
		product.setCategory("Fashion");
		product.setRating(2.3);
		product.setDescription("High quality clothing");
		product.setProductImg("/assets/cloths/mens/shirt1.jpg");
		product.setType("TV");
		productService.addProduct(product);

	}
}
