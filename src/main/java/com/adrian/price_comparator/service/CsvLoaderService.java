package com.adrian.price_comparator.service;

import com.adrian.price_comparator.model.Product;
import com.adrian.price_comparator.util.CSVUtils;
import com.adrian.price_comparator.util.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;

@Service
public class CsvLoaderService implements CommandLineRunner {

    private final ProductService productService;

    public CsvLoaderService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        loadAllCsvFiles("src/main/resources/csv/");
    }

    public void loadAllCsvFiles(String folderPath) {
        try {
            Path folder = Paths.get(folderPath);

            DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.csv");

            for (Path filePath : stream) {
                String filename = filePath.getFileName().toString();

                // Sari peste fișierele de reduceri
                if (filename.toLowerCase().contains("discount")) continue;

                LocalDate date = FileUtils.extractDateFromFilename(filename);
                String store = extractStoreFromFilename(filename);

                List<Product> products = CSVUtils.readProductsCsv(filePath.toString(), date);

                // Salvăm produsele în baza de date
                productService.saveAll(products);

                /*
                System.out.println("Produse incarcate pentru magazinul " + store + " la data " + date + ":");
                for (Product p : products) {
                    System.out.println(p);
                }
                System.out.println("----------------------------------------------------");

                 */

                System.out.println("Produse incarcate pentru magazinul " + store + " la data " + date + ": " + products.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractStoreFromFilename(String filename) {
        int underscoreIndex = filename.indexOf('_');
        if (underscoreIndex > 0) {
            return filename.substring(0, underscoreIndex).toLowerCase();
        }
        return "unknown";
    }
}
