package com.adrian.price_comparator.service;

import com.adrian.price_comparator.model.Discount;
import com.adrian.price_comparator.util.DiscountCSVUtils;
import com.adrian.price_comparator.util.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class DiscountLoaderService implements CommandLineRunner {

    private final DiscountService discountService;

    public DiscountLoaderService(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public void run(String... args) throws IOException {
        loadDiscountsFromCsv();
    }

    public void loadDiscountsFromCsv() throws IOException {
        File folder = new ClassPathResource("csv").getFile();

        File[] csvFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv") && name.contains("discount"));

        if (csvFiles == null) return;

        for (File file : csvFiles) {
            String filename = file.getName();

            try {
                LocalDate date = FileUtils.extractDateFromFilename(filename);
                String store = extractStoreFromFilename(filename);

                List<Discount> discounts = DiscountCSVUtils.readDiscountCSV(file.getPath());
                discountService.saveAllDiscounts(discounts);

                System.out.println("Reducerile încărcate pentru magazinul " + store + " la data " + date + ": " + discounts.size());

                /*
                System.out.println("Detalii reduceri încărcate din " + filename + ":");
                for (Discount discount : discounts) {
                    System.out.println(discount);
                }

                 */

            } catch (Exception e) {
                System.err.println("Eroare la procesarea fișierului: " + filename + " -> " + e.getMessage());
            }
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
