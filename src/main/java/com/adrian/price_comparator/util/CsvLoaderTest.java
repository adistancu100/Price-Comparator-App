package com.adrian.price_comparator.util;

import com.adrian.price_comparator.model.Product;
import com.adrian.price_comparator.service.CsvLoaderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Component
public class CsvLoaderTest implements CommandLineRunner {

    private final CsvLoaderService csvLoaderService;

    public CsvLoaderTest(CsvLoaderService csvLoaderService) {
        this.csvLoaderService = csvLoaderService;
    }

    @Override
    public void run(String... args) throws Exception {
      //  csvLoaderService.loadAllCsvFiles("src/main/resources/csv");
    }
}
