package com.vague.service.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Users {
    @Id
    private String login;
    private String password, gender, place, name, info, phone;
    private int age;
    private boolean role;

    public Users(){}

    public Users(String login, String password, int age) {
        this.login = login;
        this.password = password;
        this.age = age;
        this.role = false;
    }

    public Users(String login, String password, String gender,
                 String place, String name, String info, String phone,
                 int age, boolean role) {
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.place = place;
        this.name = name;
        this.info = info;
        this.phone = phone;
        this.age = age;
        this.role = role;
    }
}
