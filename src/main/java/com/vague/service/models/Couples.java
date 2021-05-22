package com.vague.service.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Couples {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "login1")
    private Users user1;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "login2")
    private Users user2;

    private boolean like1;
    private boolean like2;

    public Couples(){}

    public Couples(Users user1, Users user2, boolean like1, boolean like2) {
        this.user1 = user1;
        this.user2 = user2;
        this.like1 = like1;
        this.like2 = like2;
    }


}
