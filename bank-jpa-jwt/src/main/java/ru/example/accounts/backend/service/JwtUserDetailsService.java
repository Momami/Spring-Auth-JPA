package ru.example.accounts.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.example.accounts.backend.dao.AccountRepository;
import ru.example.accounts.backend.model.Account;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    private AccountRepository accountRepository;

    @Autowired
    public JwtUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Account user = accountRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with name: " + name);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                new ArrayList<>());
    }

}
