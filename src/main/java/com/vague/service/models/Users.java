package com.vague.service.models;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
public class Users {
    //надо навесить аннотации на ограничение количества символов в пароле и логине и что нельзя чисто чтобы пароль и логин из пробелов состояли
    //@NotBlank
    //@Size(min=3, max=12, message = "Логин должен быть не меньше 3 и не более 12 символов")
    @Id
    @Column(length = 20)
    private String login;

    @Column(length = 20)
    private String password;

    @Column(length = 10)
    private String gender;

    @Column(length = 30)
    private String place;

    @Column(length = 50)
    private String name;

    @Column(length = 300)
    private String info;

    @Column(length = 11)
    private String phone;

    @Column(length = 90)
    private int age;
    @Column(columnDefinition = "boolean default true")
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
}
