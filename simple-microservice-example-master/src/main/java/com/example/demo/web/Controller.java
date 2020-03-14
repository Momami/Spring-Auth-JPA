package com.example.demo.web;

import com.example.demo.model.Account;
import com.example.demo.backend.Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The MSA Controller.
 */
@RestController
@RequestMapping("account")
@Api(value = "customer-service", description = "Main service")
public class Controller {

    @Autowired
    private Service service;

    /**
     * Create account response account.
     *
     * @param account the account
     * @return the response account
     */
    @PostMapping("create")
    @ApiOperation(value = "Create account")
    public ResponseEntity createAccount(@RequestBody Account account) {
        return new ResponseEntity(service.create(account.getName(), account.getAmount()), HttpStatus.OK);
    }

    /**
     * Read entity response entity.
     *
     * @param name the name
     * @return the response entity
     */
    @GetMapping("get")
    @ApiOperation(value = "Get account")
    public ResponseEntity readAccount(@RequestHeader String name) {
        return new ResponseEntity(service.read(name), HttpStatus.OK);
    }

    /**
     * Update entity response entity.
     *
     * @param name   the name
     * @param amount the amount
     * @return the response entity
     */
    @PutMapping("update/{name}")
    @ApiOperation(value = "Update account")
    public ResponseEntity updateAccount(@PathVariable String name,
                                        @RequestHeader double amount) {
        return new ResponseEntity(service.update(name, amount), HttpStatus.OK);
    }

    /**
     * Delete entity response entity.
     *
     * @param name the name
     * @return the response entity
     */
    @DeleteMapping("delete/{name}")
    @ApiOperation(value = "Delete account")
    public ResponseEntity deleteAccount(@PathVariable String name) {
        return new ResponseEntity(service.delete(name), HttpStatus.OK);
    }


}
