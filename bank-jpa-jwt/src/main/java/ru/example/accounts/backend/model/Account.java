package ru.example.accounts.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Account entity model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "name")
    private String name;
    @Column
    private double amount;
    @Column
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Some> someList;

    public Account(String phone, String name, String password) {
        this.phone = phone;
        this.name = name;
        this.amount = 0;
        this.password = password;
    }

    public Account(String phone, String name, double amount, String password, Set<Some> someList) {
        this.phone = phone;
        this.name = name;
        this.amount = 0;
        this.password = password;
        this.someList = someList;
    }


    public void setSomeList(Some some) {
        if ( this.someList ==null){
            this.someList = new HashSet<>();
        }
        someList.add(some);
    }
}
