package com.adrian.price_comparator.repository;

import com.adrian.price_comparator.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public void saveAll(List<Product> productsToSave) {
        products.clear();
        products.addAll(productsToSave);
    }

    public Optional<Product> findById(String productId) {
        return products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst();
    }
}
