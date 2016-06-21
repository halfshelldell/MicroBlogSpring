package com.ironyard;

import javax.persistence.*;

/**
 * Created by illladell on 6/20/16.
 */

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }
}
