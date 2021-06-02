package com.vague.service.controllers;

import com.vague.service.models.BlackList;
import com.vague.service.models.Couples;
import com.vague.service.models.Role;
import com.vague.service.models.Users;
import com.vague.service.repo.BlackListRepo;
import com.vague.service.repo.CouplesRepo;
import com.vague.service.repo.UsersRepo;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;

@Controller
//@RequestMapping
@AllArgsConstructor
//@CrossOrigin
@Log
public class MainController {

    private final UsersRepo usersRepo;
    private final CouplesRepo couplesRepo;
    private final BlackListRepo blackListRepo;

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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginUser = ((UserDetails)principal).getUsername();
        model.addAttribute("users", usersRepo.matchsForUsersByLogin(loginUser));
        return "match";
    }

    @GetMapping(value = "/user/questionnaires/{age}")
    public String getQuestionnaires(@PathVariable int age, Model model){
        //List<String> Delete = couplesRepo
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginUser = ((UserDetails)principal).getUsername();
        model.addAttribute("age1", age);
        model.addAttribute("users", usersRepo.findNeedUsers(loginUser));
        return "questionnaires";
    }

    //@GetMapping(value = "/user/getUsers/{age}")
    //public String getUsers(@PathVariable int age, Model model){
        /*System.out.print("getUsers ");
        System.out.println(age);
        //if (age == 0)
        model.addAttribute("age1", age);
        model.addAttribute("users", usersRepo.findAll());
        System.out.println(2);*/

        //return "questionnaires";

        //return "redirect:/user/questionnaires/" + age;
    //}

    /*@GetMapping(value = "/user/selectUser/{age}")
    public String selectUsers(@PathVariable int age){
        System.out.println(3);
        System.out.println(age);
        return "redirect:/user/getUsers/" + age;
    }*/

    @GetMapping(value = "/user/settings")
    public String getSettings(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login;
        if (principal instanceof UserDetails) {
            login = ((UserDetails)principal).getUsername();
        } else {
            login = principal.toString();
        }
        model.addAttribute("user", usersRepo.findById(login));
        return "settings";
    }

    @PostMapping(value  = "/user/settings")
    public String updateUser(@ModelAttribute Users users){
        usersRepo.save(users);
        return "settings";
    }



    @PostMapping(value = "/user/like/{loginLike}")
    public String userLike(@PathVariable String loginLike){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginUser = ((UserDetails)principal).getUsername();
        Users userLike = usersRepo.findByLogin(loginLike);
        Users thisUser = usersRepo.findByLogin(loginUser);
        if(couplesRepo.countAllByUser1_LoginAndUser2_Login(loginLike, loginUser) == 1){
            couplesRepo.save(new Couples(userLike, thisUser, true, true));
        } else
            couplesRepo.save(new Couples(thisUser, userLike, true, false));

        return "redirect:/user/questionnaires/0";
    }

    @GetMapping(value = "/admin/adminmain")
    //@PreAuthorize("hasRole('ADMIN')")
    public String adminMainView(){
        return "adminmain";
    }

    @GetMapping(value = "/admin/statistics")
    public String statisticsView(){
        return "statistics";
    }

    @GetMapping(value = "/admin/blacklist")
    public String blackListView(Model model){
        model.addAttribute("users", blackListRepo.findAll());
        return "blacklist";
    }

    @GetMapping(value = "/admin/blacklist/ban/{login}")
    public String banUser(@PathVariable String login){
        if(usersRepo.countAllByLogin(login) == 1 && blackListRepo.countAllByLogin(login) == 0) {
            Users user = usersRepo.findByLogin(login);
            user.setActive(false);
            Date dateNow = new java.sql.Date((new java.util.Date()).getTime());
            blackListRepo.save(new BlackList(login, dateNow));
        }
        return "redirect:/admin/blacklist";
    }

    @GetMapping(value = "/admin/blacklist/unban/{login}")
    public String unbanUser(@PathVariable String login){
        if(usersRepo.countAllByLogin(login) == 1 && blackListRepo.countAllByLogin(login) == 1) {
            Users user = usersRepo.findByLogin(login);
            user.setActive(true);
            blackListRepo.deleteById(login);
        }
        return "redirect:/admin/blacklist";
    }

    @RequestMapping(path = "/success")
    public String getDefaultPage(){
        Collection<SimpleGrantedAuthority> authorities =
                (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if(authorities.containsAll(Role.USER.getAuthorities())){
            return "redirect:/user/usermain";
        }
        else if(authorities.containsAll(Role.ADMIN.getAuthorities())) {
            return "redirect:/admin/adminmain";
        }
        else
            return "redirect:/";
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
