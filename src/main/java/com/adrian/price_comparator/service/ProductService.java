package com.adrian.price_comparator.service;

import com.adrian.price_comparator.model.Product;
import com.adrian.price_comparator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void loadProductsFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream(fileName), StandardCharsets.UTF_8))) {

            String line;
            List<Product> products = new ArrayList<>();

            // Sărim peste header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                // Asumând structura: productId,name,category,brand,quantity,unit,price,currency,date
                Product p = new Product(
                        tokens[0],
                        tokens[1],
                        tokens[2],
                        tokens[3],
                        Double.parseDouble(tokens[4]),
                        tokens[5],
                        Double.parseDouble(tokens[6]),
                        tokens[7],
                        LocalDate.parse(tokens[8])
                );
                products.add(p);
            }
            productRepository.saveAll(products);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load products from CSV");
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
