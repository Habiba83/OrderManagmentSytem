package com.OrderManagmentSystem.Models.OrderModels;
public class ProductReq {
    private String name;
    private long serialNum;
    private int count;
    public ProductReq() {
    }
    public ProductReq(String name, long serialNum, int count) {
        this.name = name;
        this.serialNum = serialNum;
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

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public String toString() {
        return "ProductModel{" +
                "name='" + name + '\'' +
                ", serialNum=" + serialNum +
                ", count=" + count +
                '}';
    }
}
