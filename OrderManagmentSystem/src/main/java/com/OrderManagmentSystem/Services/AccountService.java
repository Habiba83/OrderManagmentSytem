package com.OrderManagmentSystem.Services;

import com.OrderManagmentSystem.Models.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountService {

    private final ArrayList<Account> accounts;

    public AccountService() {
        this.accounts = new ArrayList<>();
    }

    public boolean addAccount(Account newAccount) {
        for (Account account : accounts) {
            if (account.getUserName().equals(newAccount.getUserName())) {
                return false; // Username already exists
            } else if (account.getEmail().equals(newAccount.getEmail())) {
                return false; // Email already registered
            }
        }
        accounts.add(newAccount);
        return true; // Account created successfully
    }

    public boolean checkUser(Account checkAccount) {
        for (Account account : accounts) {
            if (account.getUserName().equals(checkAccount.getUserName())) {
                return account.getPassword().equals(checkAccount.getPassword());
            }
        }
        return false;
    }
}
