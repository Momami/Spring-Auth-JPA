package ru.example.accounts.backend.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.accounts.backend.dao.AccountRepository;
import ru.example.accounts.backend.model.Account;

import java.util.List;

/**
 * The  Account service class.
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;


    /**
     * Get account by phone.
     *
     * @param phone the phone
     * @return the account
     */
    public Account get(String phone) {
        return accountRepository.findByPhone(phone);
    }

    /**
     * Delete account operation.
     *
     * @param phone the phone
     * @return the account
     */
    public void delete(String phone) {
        Account account = accountRepository.findByPhone(phone);
        accountRepository.delete(account);
    }



    /**
     * Get all list accounts.
     *
     * @return the list
     */
    public List<Account> getAll(){
        return Lists.newArrayList( accountRepository.findAll());
    }
}
