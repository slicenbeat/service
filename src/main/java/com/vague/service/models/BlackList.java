package com.vague.service.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class BlackList {

    @Id
    //@OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "")
    //private Users users;
    private String login;

    private Date date_of_ban;

    public BlackList(){}

    public BlackList(String login, Date date_of_ban) {
        this.login = login;
        this.date_of_ban = date_of_ban;
    }
}
