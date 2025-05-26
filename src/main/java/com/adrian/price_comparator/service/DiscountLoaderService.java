package com.adrian.price_comparator.service;

import com.adrian.price_comparator.model.Discount;
import com.adrian.price_comparator.service.DiscountService;
import com.adrian.price_comparator.util.DiscountCSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountLoaderService {

    private final DiscountService discountService;

    @Autowired
    public DiscountLoaderService(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostConstruct
    public void loadDiscountsFromCsv() throws IOException {
        File folder = new ClassPathResource("csv").getFile();

        File[] csvFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv") && name.contains("discount"));

        if (csvFiles == null) return;

        List<Discount> allDiscounts = new ArrayList<>();

        for (File file : csvFiles) {
            String filename = file.getName();

            try {
                String[] parts = filename.replace(".csv", "").split("_");
                String dateString = parts[parts.length - 1];
                LocalDate date = LocalDate.parse(dateString);

                List<Discount> discounts = DiscountCSVUtils.readDiscountCSV(file.getPath());
                allDiscounts.addAll(discounts);
            } catch (Exception e) {
                System.err.println("Eroare la procesarea fișierului: " + filename + " -> " + e.getMessage());
            }
        }

        discountService.saveAllDiscounts(allDiscounts);
        System.out.println("Reducerile au fost încărcate: " + allDiscounts.size());
    }
}
