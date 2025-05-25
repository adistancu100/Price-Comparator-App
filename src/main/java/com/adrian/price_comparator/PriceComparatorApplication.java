package com.adrian.price_comparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
public class PriceComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceComparatorApplication.class, args);
	}

}
