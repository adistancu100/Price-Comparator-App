package com.adrian.price_comparator.service;

import com.adrian.price_comparator.model.Product;
import com.adrian.price_comparator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void loadProductsFromCSV(String fileName) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new RuntimeException("CSV file not found: " + fileName);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                String line;
                List<Product> products = new ArrayList<>();

                br.readLine(); // skip header

                while ((line = br.readLine()) != null) {
                    String[] tokens = line.trim().split(";");

                    // Ignoră liniile care nu au exact 9 câmpuri
                    if (tokens.length != 8) {
                        System.err.println("Skipping invalid line (wrong number of fields): " + line + " " + tokens.length);
                        continue;
                    }

                    try {
                        Product p = new Product(
                                tokens[0].trim(),
                                tokens[1].trim(),
                                tokens[2].trim(),
                                tokens[3].trim(),
                                Double.parseDouble(tokens[4].trim()),
                                tokens[5].trim(),
                                Double.parseDouble(tokens[6].trim()),
                                tokens[7].trim()
                                //LocalDate.parse(tokens[8].trim())
                        );
                        products.add(p);
                    } catch (Exception parseEx) {
                        System.err.println("Skipping invalid line (parse error): " + line);
                        parseEx.printStackTrace();
                    }
                }

                productRepository.saveAll(products);
               // System.out.println("Loaded " + products.size() + " valid products from: " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load products from CSV");
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }
}
