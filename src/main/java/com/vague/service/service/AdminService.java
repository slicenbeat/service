package com.vague.service.service;

import com.vague.service.models.BlackList;
import com.vague.service.models.Role;
import com.vague.service.models.Users;
import com.vague.service.repo.BlackListRepo;
import com.vague.service.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private BlackListRepo blackListRepo;

    public void blackList(Model model){
        model.addAttribute("users", blackListRepo.findAll());
        model.addAttribute("users", blackListRepo.findAll());
    }

    public void banByLogin(String login){
        if(usersRepo.countAllByLogin(login) == 1 && blackListRepo.countAllByLogin(login) == 0 &&
                !(usersRepo.findByLogin(login).getRole().equals(Collections.singleton(Role.ADMIN)))) {
            Users user = usersRepo.findByLogin(login);
            user.setActive(false);
            Date dateNow = new java.sql.Date((new java.util.Date()).getTime());
            blackListRepo.save(new BlackList(login, dateNow));
        }
    }

    public void unbanByLogin(String login) {
        if(usersRepo.countAllByLogin(login) == 1 && blackListRepo.countAllByLogin(login) == 1) {
            Users user = usersRepo.findByLogin(login);
            user.setActive(true);
            blackListRepo.deleteById(login);
        }
    }

    public void getStatistic(Model model){
        List<String> ageDiff = usersRepo.StatistAgeDifference();
        List<ClassForGetStat> splitStatOne = new ArrayList<ClassForGetStat>();
        for (int i = 0; i < ageDiff.size(); i++){
            splitStatOne.add(new ClassForGetStat(ageDiff.get(i).split(",")[0], ageDiff.get(i).split(",")[1]));
        }
        model.addAttribute("ageDifference", splitStatOne);

        List<String> genderCouples = usersRepo.StatistGenderCouples();
        List<ClassForGetStat> splitStatTwo = new ArrayList<ClassForGetStat>();
        for (int i = 0; i < genderCouples.size(); i++){
            splitStatTwo.add(new ClassForGetStat(genderCouples.get(i).split(",")[0] + " + " +
                    genderCouples.get(i).split(",")[1], genderCouples.get(i).split(",")[2]));
        }
        model.addAttribute("genderCouples", splitStatTwo);
    }
}
