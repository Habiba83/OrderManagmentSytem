package com.OrderManagmentSystem.Models;

public class Customer {

    private String userName;
    private String password;
    private String address;
    private String email;
    private String id;
    private double balance;


    public Customer(String userName, String password, String address, String email, String id, double balance) {
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.id = id;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deductBalance(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            // Handle invalid amount
            throw new RuntimeException("Insufficient balance or invalid amount");
        }
    }

    public void addBalance(double shippingFees) {
        balance += shippingFees;
    }
}
