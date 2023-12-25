package com.OrderManagmentSystem.Controllers;

import com.OrderManagmentSystem.Models.Account;
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
    public String createAccount(@RequestBody Account newAccount) {
        boolean success = accountService.addAccount(newAccount);
        return success ? "Account created successfully :)" : "Account creation failed. Username or email already exists.";
    }

    @PostMapping("/login")
    public String login(@RequestBody Account checkAccount) {
        boolean success = accountService.checkUser(checkAccount);
        return success ? "Logged in successfully :)" : "Login failed. Username or password is incorrect.";
    }
}
