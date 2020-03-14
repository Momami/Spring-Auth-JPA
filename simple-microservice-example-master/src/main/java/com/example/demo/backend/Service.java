package com.example.demo.backend;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * The  Service.
 */
@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Dao dao;

    /**
     * Create string service.
     *
     * @param name   the name
     * @param amount the amount
     * @return the string
     */
    public String create(String name, double amount) {

        return dao.create(name, amount);
    }

    /**
     * Read string service.
     *
     * @param name the name
     * @return the string
     */
    public String read(String name) {
        return dao.read(name);
    }

    /**
     * Update string service.
     *
     * @param name   the name
     * @param amount the amount
     * @return the string
     */
    public String update(String name, double amount) {
        return dao.update(name, amount);
    }

    /**
     * Delete string service.
     *
     * @param name the name
     * @return the string
     */
    public String delete(String name) {
        return dao.delete(name);
    }


}
