package com.OrderManagmentSystem.Services;

import com.OrderManagmentSystem.Models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountService {

    private final ArrayList<Customer> customers;

    public AccountService() {
        this.customers = new ArrayList<>();
    }

    public boolean addAccount(Customer newCustomer) {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(newCustomer.getUserName())) {
                return false; // Username already exists
            } else if (customer.getEmail().equals(newCustomer.getEmail())) {
                return false; // Email already registered
            }
        }
        customers.add(newCustomer);
        return true; // Account created successfully
    }

    public boolean checkUser(Customer checkCustomer) {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(checkCustomer.getUserName())) {
                return customer.getPassword().equals(checkCustomer.getPassword());
            }
        }
        return false;
    }
}
