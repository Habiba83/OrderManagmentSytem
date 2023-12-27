package com.OrderManagmentSystem.Models;
public class Product {
    private String name;
    private long serialNum;
    private String vendor;
    private String category;
    private double price;
    private int count;
    public Product() {
    }
    public Product(String name, long serialNum, String vendor, String category, double price, int count) {
        this.name = name;
        this.serialNum = serialNum;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.count = count;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public long getSerialNum() {
        return serialNum;
    }
    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getCount() {
        return count;
    }
    public void addCount(int value) {
        count = count + value;
    }
    public void removeCount(int value) {
        count = count - value;
    }


    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public String toString() {
        return "ProductModel{" +
                "name='" + name + '\'' +
                ", serialNum=" + serialNum +
                ", vendor='" + vendor + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
