package com.chat.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends DefaultEntity {
    private String login;
    private String password;
}