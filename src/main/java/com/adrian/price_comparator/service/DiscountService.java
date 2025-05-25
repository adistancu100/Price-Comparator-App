package com.adrian.price_comparator.service;

import com.adrian.price_comparator.repository.DiscountRepository;
import com.adrian.price_comparator.model.Discount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public void saveAllDiscounts(List<Discount> discounts) {
        discountRepository.saveAll(discounts);
    }

    public Optional<Discount> getDiscountByProductId(String productId) {
        return discountRepository.findByProductId(productId);
    }
}
