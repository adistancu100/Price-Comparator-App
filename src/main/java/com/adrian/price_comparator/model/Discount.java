package com.adrian.price_comparator.model;

import java.time.LocalDate;

public class Discount {

    private String productId;
    private String productName;
    private String brand;
    private double quantity;
    private String unit;
    private String category;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int percentageDiscount;

    public Discount() {}

    public Discount(String productId, String productName, String brand, double quantity, String unit, String category, LocalDate fromDate, LocalDate toDate, int percentageDiscount) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.percentageDiscount = percentageDiscount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", category='" + category + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", percentageDiscount=" + percentageDiscount +
                '}';
    }
}
