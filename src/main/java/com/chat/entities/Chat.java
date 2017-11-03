package com.chat.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "chat")
public class Chat extends DefaultEntity {
    @ManyToOne
    private User user;
    private String message;
    private String login;
    private String date;
}

