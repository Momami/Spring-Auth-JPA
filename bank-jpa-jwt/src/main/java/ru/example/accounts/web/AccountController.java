package ru.example.accounts.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.example.accounts.backend.dao.SomeRepository;
import ru.example.accounts.backend.model.Some;
import ru.example.accounts.backend.security.JwtToken;
import ru.example.accounts.backend.service.JwtUserDetailsService;
import ru.example.accounts.backend.dao.AccountRepository;
import ru.example.accounts.backend.model.Account;
import ru.example.accounts.backend.model.JwtRequest;
import ru.example.accounts.backend.service.AccountService;

import javax.xml.bind.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Account controller.
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService service;
    private AuthenticationManager authenticationManager;
    private AccountRepository accountRepository;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtToken jwtToken;
    private SomeRepository someRepository;

    @Autowired
    public AccountController(AccountService service, AuthenticationManager authenticationManager, AccountRepository accountRepository, JwtUserDetailsService jwtUserDetailsService, JwtToken jwtToken, SomeRepository someRepository) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtToken = jwtToken;
        this.someRepository = someRepository;
    }




    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/signup")
    public Boolean create(@RequestBody Account body) throws  ValidationException {
        if (accountRepository.existsAccountByName(body.getName())) {
            throw new ValidationException("Username already existed");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(body.getPassword());
        accountRepository.save(new Account(body.getPhone(), body.getName(), encodedPassword));
        return true;
    }

    @PostMapping("/createSome")
    public Boolean createSome(@RequestBody String currency) throws  ValidationException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        Account account = accountRepository.findByName(username);
        Some some = new Some(currency);
        someRepository.save(some);
        account.setSomeList(some);
        accountRepository.save(account);
        return true;
    }

    /**
     * Gets account operation.
     *
     * @param phone the phone
     * @return the account
     */
    @GetMapping("/info/{phone}")
    @ApiOperation("get account")
    public Account getAccount(@PathVariable("phone") String phone) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        System.out.println(username);
        return service.get(phone);
    }

    /**
     * Delete account operation.
     *
     * @param phone the phone
     * @return the account
     */
    @DeleteMapping("/delete/{phone}")
    @ApiOperation("delete account")
    public void deleteAccount(@PathVariable("phone") String phone) {

        service.delete(phone);
    }


    /**
     * Gets account list operation.
     *
     * @return the account
     */
    @GetMapping("/info/all")
    @ApiOperation("get all account")
    public List<Account> getAccount() {
        return service.getAll();
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

}
