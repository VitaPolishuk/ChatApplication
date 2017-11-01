package com.chat.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "chat")
public class Chat extends DefaultEntity {
    @ManyToOne
    private User user;
    private String message;
    private Date date;
}

