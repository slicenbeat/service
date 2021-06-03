package com.vague.service.controllers;

import com.vague.service.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
@Log
public class AdminController {

    private final AdminService adminService;

    @GetMapping(value = "/admin/adminmain")
    public String adminMainView(){
        return "adminmain";
    }

    @GetMapping(value = "/admin/statistics")
    public String statisticsView(){
        return "statistics";
    }

    @GetMapping(value = "/admin/blacklist")
    public String blackListView(Model model){
        adminService.blackList(model);
        return "blacklist";
    }

    @GetMapping(value = "/admin/blacklist/ban/{login}")
    public String banUser(@PathVariable String login){
        adminService.banByLogin(login);
        return "redirect:/admin/blacklist";
    }

    @GetMapping(value = "/admin/blacklist/unban/{login}")
    public String unbanUser(@PathVariable String login){
        adminService.unbanByLogin(login);
        return "redirect:/admin/blacklist";
    }

    @GetMapping(value = "/admin/statistics/get")
    public String getStatistics(Model model){
        adminService.getStatistic(model);
        return "statistics";
    }
}
