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

        List<ClassForGetStat> Statist = new ArrayList<>();
        Statist.add(new ClassForGetStat("Мужской + Женский", Double.toString(usersRepo.StatCoupGend("Мужской", "Женский") +
                usersRepo.StatCoupGend("Женский", "Мужской"))));
        Statist.add(new ClassForGetStat("Женский + Женский", Double.toString(usersRepo.StatCoupGend("Женский", "Женский"))));
        Statist.add(new ClassForGetStat("Мужской + Мужской", Double.toString(usersRepo.StatCoupGend("Мужской", "Мужской"))));
        model.addAttribute("genderCouples", Statist);
    }
}
