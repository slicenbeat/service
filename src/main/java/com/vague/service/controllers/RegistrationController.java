package com.vague.service.controllers;

import com.vague.service.models.Role;
import com.vague.service.models.Users;
import com.vague.service.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Users user, Map<String, Object> model) {
        Users userFromDb = usersRepo.findByLogin(user.getLogin());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRole(Collections.singleton(Role.USER));
        usersRepo.save(user);

        return "redirect:/login";
    }
}
