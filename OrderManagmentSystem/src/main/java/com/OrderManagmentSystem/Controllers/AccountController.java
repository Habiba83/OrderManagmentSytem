package com.OrderManagmentSystem.Controllers;

import com.OrderManagmentSystem.Models.Customer;
import com.OrderManagmentSystem.Services.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public String createAccount(@RequestBody Customer newCustomer) {
        boolean success = accountService.addAccount(newCustomer);
        return success ? "Account created successfully :)" : "Account creation failed. Username or email already exists.";
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer checkCustomer) {
        boolean success = accountService.checkUser(checkCustomer);
        return success ? "Logged in successfully :)" : "Login failed. Username or password is incorrect.";
    }
}
