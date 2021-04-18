package com.vague.service.models;

import javax.persistence.*;
import java.util.List;

@Entity

public class Admins {
    @Id
    private String login;

    private String password;

    private boolean role;

    public  Admins(){}

    public Admins(String login, String password, boolean role, List<BlackList> black_users) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
}
