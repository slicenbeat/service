package com.vague.service.controllers;

import com.vague.service.models.Users;
import com.vague.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Log
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }

    @GetMapping(value = "/user/usermain")
    public String userMainView(){
        return "usermain";
    }

    @GetMapping(value = "/user/match")
    public String getMatch(Model model){
        userService.getMatch(model);
        return "match";
    }

    @GetMapping(value = "/user/questionnaires")
    public String getQuestionnaires(Model model){
        userService.getQuestionnaires(model);
        return "questionnaires";
    }

    @GetMapping(value = "/user/settings")
    public String getSettings(Model model){
        userService.getSettings(model);
        return "settings";
    }

    @PostMapping(value  = "/user/settings")
    public String updateUser(@ModelAttribute Users user) {
        userService.updateUser(user);
        return "redirect:/success";
    }

    @PostMapping(value = "/user/like/{loginLike}")
    public String userLike(@PathVariable String loginLike){
        userService.userLike(loginLike);
        return "redirect:/user/questionnaires";
    }

    @RequestMapping(path = "/success")
    public String getDefaultPage(){
        return userService.getDefaultPage();
    }

}
