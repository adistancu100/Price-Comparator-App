package com.adrian.price_comparator;

import com.adrian.price_comparator.model.Discount;
import com.adrian.price_comparator.util.DiscountCSVUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
public class PriceComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceComparatorApplication.class, args);

		String filePath = "src/main/resources/csv/kaufland_discounts_2025-05-01.csv";
		List<Discount> discounts = DiscountCSVUtils.readDiscountCSV(filePath);
		discounts.forEach(System.out::println);
	}

}
