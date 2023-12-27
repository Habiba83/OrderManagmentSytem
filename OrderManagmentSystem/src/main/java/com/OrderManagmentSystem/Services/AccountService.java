package com.OrderManagmentSystem.Services;

import com.OrderManagmentSystem.Models.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class AccountService {

    private final ArrayList<Customer> customers;

    public AccountService() {
        this.customers = new ArrayList<>();
    }

    public Customer addAccount(Customer newCustomer) {
        customers.add(newCustomer);
        return newCustomer;
    }

    public boolean checkUser(Customer checkCustomer) {
        for (Customer customer : customers) {
            if (customer.getUserName().equals(checkCustomer.getUserName())) {
                return customer.getPassword().equals(checkCustomer.getPassword());
            }
        }
        return false;
    }

    public List<Customer> getAllCustomers() {
        return customers;

    }

    public Customer getCustomer(String customerId){
        for (Customer customer : customers){
            if (customer.getId().equals(customerId)){
                return customer;
            }
        }
        return null;
    }
}
