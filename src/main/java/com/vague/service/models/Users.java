package com.vague.service.models;

import javax.persistence.*;

@Entity
public class Users {
    @Id
    private String login;
    private String password, gender, place, name, info, phone;
    private int age;

    public Users(){}

    public Users(String login, String password, String gender, String place, String name, String info, String phone, int age) {
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.place = place;
        this.name = name;
        this.info = info;
        this.phone = phone;
        this.age = age;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) { this.place = place; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) { this.phone = phone; }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
