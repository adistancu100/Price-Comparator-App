package com.adrian.price_comparator.util;

import com.adrian.price_comparator.model.Discount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountCSVUtils {
    public static List<Discount> readDiscountCSV(String filePath) {
        List<Discount> discounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(";");

                if (values.length < 9) continue;
                Discount discount = new Discount();
                discount.setProductId(values[0]);
                discount.setProductName(values[1]);
                discount.setBrand(values[2]);
                discount.setQuantity(Double.parseDouble(values[3]));
                discount.setUnit(values[4]);
                discount.setCategory(values[5]);
                discount.setFromDate(LocalDate.parse(values[6]));
                discount.setToDate(LocalDate.parse(values[7]));
                discount.setPercentageDiscount(Integer.parseInt(values[8]));
                discounts.add(discount);

            }
        } catch (IOException e){
            System.err.println("error reading discount csv file" + filePath + ": " + e.getMessage());
        }


        return discounts;
    }
}
