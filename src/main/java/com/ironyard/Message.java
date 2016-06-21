package com.ironyard;

import javax.persistence.*;

/**
 * Created by illladell on 6/20/16.
 */

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String text;

    public Message(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Message() {
    }
}
