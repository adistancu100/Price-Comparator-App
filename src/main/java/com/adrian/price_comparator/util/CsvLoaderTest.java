package com.adrian.price_comparator.util;

import com.adrian.price_comparator.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Component
class CsvLoaderTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        String path = Paths.get("src", "main", "resources", "csv", "lidl_2025-05-01.csv").toString();
        LocalDate date = LocalDate.of(2025, 5, 1);

        List<Product> products = CSVUtils.readProductsCsv(path,date);

        System.out.println("Produse incarcate din CSV: ");
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
