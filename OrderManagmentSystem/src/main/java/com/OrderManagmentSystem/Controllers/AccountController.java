package com.OrderManagmentSystem.Controllers;

import com.OrderManagmentSystem.Models.Customer;
import com.OrderManagmentSystem.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public Customer createAccount(@RequestBody Customer customer) {
        System.out.println(customer);
        return  accountService.addAccount(customer);
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer checkCustomer) {
        boolean success = accountService.checkUser(checkCustomer);
        return success ? "Logged in successfully :)" : "Login failed. Username or password is incorrect.";
    }

    @GetMapping("")
    public List<Customer> getAllCustomers(){
        return accountService.getAllCustomers();
    }
    @GetMapping("/{customerId}")
    public Customer getSpecificCustomer(@PathVariable String customerId){
        return accountService.getCustomer(customerId);
    }
}
