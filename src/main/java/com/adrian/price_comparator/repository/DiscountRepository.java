package com.adrian.price_comparator.repository;

import com.adrian.price_comparator.model.Discount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DiscountRepository {
    private final List<Discount> discounts = new ArrayList<>();

    public List<Discount> findAll() {
        return new ArrayList<>(discounts);
    }

    public void saveAll(List<Discount> discountsToSave) {
        discounts.clear();
        discounts.addAll(discountsToSave);
    }

    public Optional<Discount> findByProductId(String productId) {
        return discounts.stream()
                .filter(d -> d.getProductId().equals(productId))
                .findFirst();
    }
}
