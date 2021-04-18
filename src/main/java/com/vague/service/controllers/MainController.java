package com.vague.service.controllers;
import com.vague.service.models.Users;
import com.vague.service.repo.UsersRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vague")
@AllArgsConstructor
@Log
@CrossOrigin
public class MainController {

    private final UsersRepo usersRepo;

    @GetMapping("/login")
    public String index(){
        return "index";
    }

    @PostMapping()
    public /*@ResponseBody*/ Users newUser(@RequestBody Users user){
        return usersRepo.save(user);
        //return "match";
    }

    //@GetMapping("/match")
    //public String Match()



}
