package com.vague.service.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Users {
    @Id
    private String login;
    private String password, gender, place, name, info, phone;
    private int age;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
}
