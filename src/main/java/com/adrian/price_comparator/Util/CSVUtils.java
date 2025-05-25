package com.adrian.price_comparator.Util;

import com.adrian.price_comparator.Model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static List<Product> readProductsCsv(String filePath, LocalDate date){
        List<Product> products = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }


                String[] values = line.split(";");

                if (values.length < 8) continue;

                Product product = new Product();
                product.setProductId(values[0]);
                product.setName(values[1]);
                product.setCategory(values[2]);
                product.setBrand(values[3]);
                product.setQuantity(Double.parseDouble(values[4]));
                product.setUnit(values[5]);
                product.setPrice(Double.parseDouble(values[6]));
                product.setCurrency(values[7]);
                product.setDate(date);

                products.add(product);
            }

        } catch (IOException e){
            System.err.println("Error reading file" + filePath + ": " + e.getMessage());
        }



        return products;
    }
}
