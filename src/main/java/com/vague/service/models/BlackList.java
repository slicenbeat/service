package com.vague.service.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class BlackList {


    @Id
    private String login;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login_admin")
    private Admins admin;

    private Date date_of_ban;

    public BlackList(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Admins getAdmin() {
        return admin;
    }

    public void setAdmin(Admins admin) {
        this.admin = admin;
    }

    public Date getDate_of_ban() {
        return date_of_ban;
    }

    public void setDate_of_ban(Date date_of_ban) {
        this.date_of_ban = date_of_ban;
    }
}
