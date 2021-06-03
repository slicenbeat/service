package com.vague.service.service;

import com.vague.service.models.Couples;
import com.vague.service.models.Role;
import com.vague.service.models.Users;
import com.vague.service.repo.CouplesRepo;
import com.vague.service.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private CouplesRepo couplesRepo;

    public void getMatch(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginUser = ((UserDetails)principal).getUsername();
        model.addAttribute("users", usersRepo.matchsForUsersByLogin(loginUser));
    }

    public void getQuestionnaires(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginUser = ((UserDetails)principal).getUsername();
        model.addAttribute("users", usersRepo.findNeedUsers(loginUser));
    }

    public void getSettings(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginUser = ((UserDetails)principal).getUsername();
        model.addAttribute("user", usersRepo.findById(loginUser));
    }

    public void updateUser(Users user){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginUser = ((UserDetails)principal).getUsername();
        Users users = usersRepo.findByLogin(loginUser);
        users.setPassword(user.getPassword());
        users.setName(user.getName());
        users.setAge(user.getAge());
        users.setInfo(user.getInfo());
        users.setPhone(user.getPhone());
        usersRepo.save(users);
    }

    public void userLike(String loginLike){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginUser = ((UserDetails)principal).getUsername();
        Users userLike = usersRepo.findByLogin(loginLike);
        Users thisUser = usersRepo.findByLogin(loginUser);
        if(couplesRepo.countAllByUser1_LoginAndUser2_Login(loginLike, loginUser) == 1){
            couplesRepo.save(new Couples(userLike, thisUser, true, true));
        } else
            couplesRepo.save(new Couples(thisUser, userLike, true, false));
    }

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
}
