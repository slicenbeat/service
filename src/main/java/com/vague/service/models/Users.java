package com.vague.service.models;

import lombok.Data;

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
    private String login;

    //@NotBlank
    //@Size(min=6, max=20)
    private String password;

    private String gender, place, name, info, phone;
    private int age;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    //надо с конструктором сделать что-то
    public Users() {
        this.login = "";
        this.password = "";
        this.gender = "";
        this.place = "";
        this.name = "";
        this.info = "";
        this.phone = "79270243342";
        this.age = 18;
        this.active = false;
    }
}
