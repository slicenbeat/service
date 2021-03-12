package com.vague.service.models;

import javax.persistence.*;

@Entity
public class Couples {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "login1")
    private Users user1;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "login2")
    private Users user2;

    private boolean like1;
    private boolean like2;

    public Couples(){}

    public int getId() {
        return id;
    }

    public Users getUser1() {
        return user1;
    }

    public void setUser1(Users user1) {
        this.user1 = user1;
    }

    public Users getUser2() {
        return user2;
    }

    public void setUser2(Users user2) {
        this.user2 = user2;
    }

    public boolean getLike1() {
        return like1;
    }

    public void setLike1(boolean like1) {
        this.like1 = like1;
    }

    public boolean getLike2() {
        return like2;
    }

    public void setLike2(boolean like2) {
        this.like2 = like2;
    }
}
