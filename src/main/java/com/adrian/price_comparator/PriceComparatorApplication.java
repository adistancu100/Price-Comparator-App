package com.adrian.price_comparator;

import com.adrian.price_comparator.model.Discount;
import com.adrian.price_comparator.service.ProductService;
import com.adrian.price_comparator.util.DiscountCSVUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
public class PriceComparatorApplication {

	@Autowired
	private final ProductService productService;

	public PriceComparatorApplication(ProductService productService) {
		this.productService = productService;
	}
	@PostConstruct
	public void init() {
		productService.loadProductsFromCSV("csv/kaufland_2025-05-01.csv");

	}

	public static void main(String[] args) {
		SpringApplication.run(PriceComparatorApplication.class, args);


	}


}
