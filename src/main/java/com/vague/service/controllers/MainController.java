package com.vague.service.controllers;
import com.vague.service.models.Users;
import com.vague.service.repo.BlackListRepo;
import com.vague.service.repo.UsersRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping
@AllArgsConstructor
@Log
@CrossOrigin
public class MainController {

    @GetMapping(value = "/usermain")
    public String userMainView(){
        return "usermain";
    }

    @GetMapping(value = "/match")
    public String getMatch(){
        return "match";
    }

    @GetMapping(value = "/questionnaires")
    public String getQuestionnaires(){
        return "questionnaires";
    }

    @GetMapping(value = "/settings")
    public String getSettings(){
        return "settings";
    }

    @GetMapping(value = "/adminmain")
    public String adminMainView(){
        return "adminmain";
    }

    @GetMapping(value = "/statistics")
    public String statisticsView(){
        return "statistics";
    }

    @GetMapping(value = "/blacklist")
    public String blackListView(){
        return "blacklist";
    }
    /*@GetMapping(value = "/login")
    public String getPage(@RequestBody Users user){
        System.out.println("sdfgvdfdfdvf");
        return "/blacklist";
    }*/

    /*@DeleteMapping(value = "/login")
    public void deleteUserFromBlackList(@RequestBody str login){
        System.out.println(login.getLogin());
        blackListRepo.deleteById(login.getLogin());
    }*/


    /*@GetMapping("/login")
    public ModelAndView NewView(){
        ModelAndView mav = new ModelAndView("blacklist");
        System.out.println("rjrj");
        return mav;
    }*/

    //@PostMapping()
    //public String newUser(@RequestBody Users user){
    //    return usersRepo.save(user);
    //    //return "match";
    //}

    //@GetMapping("/match")
    //public String Match()



}
