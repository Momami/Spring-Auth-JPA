package ru.example.accounts.backend.dao;

import org.springframework.data.repository.CrudRepository;
import ru.example.accounts.backend.model.Account;

public interface AccountRepository extends CrudRepository<Account,Long> {
    Account findByPhone(String phone);
    Account findByName(String name);
    boolean existsAccountByName(String name);
    boolean existsByPhone(String phone);
}
