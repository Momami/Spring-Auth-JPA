package com.example.demo.backend;

import org.springframework.stereotype.Repository;

/**
 * The  Dao.
 */
public class Dao {


    /**
     * Create string operation.
     *
     * @param name   the name
     * @param amount the amount
     * @return the string
     */
    public String create(String name, double amount) {
        return "insert in db  name: " + name + " and amount: " + amount;
    }

    /**
     * Read string operation.
     *
     * @param name the name
     * @return the string
     */
    public String read(String name) {
        return "read in db " + name;
    }

    /**
     * Update string operation.
     *
     * @param name   the name
     * @param amount the amount
     * @return the string
     */
    public String update(String name, double amount) {
        return "update in db " + name+", change amount: "+amount;
    }

    /**
     * Delete string operation.
     *
     * @param name the name
     * @return the string
     */
    public String delete(String name) {
        return "delete in db " + name;
    }
}
