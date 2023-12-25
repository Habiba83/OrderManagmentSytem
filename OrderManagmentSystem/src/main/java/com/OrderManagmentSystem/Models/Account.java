package com.OrderManagmentSystem.Models;

public class Account {

    private String userName;
    private String password;
    private String address;
    private String email;
    private double balance;
    public Account() {
    }

    public Account(String userName, String password, String address, String email, double balance) {
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.balance = balance;
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
}
