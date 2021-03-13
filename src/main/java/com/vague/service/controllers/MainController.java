package com.vague.service.controllers;
import com.vague.service.models.Couples;
import com.vague.service.models.Users;
import com.vague.service.repo.UsersRepo;
import com.vague.service.services.CouplesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //отвечает за обработку всех переходов на сайт
public class MainController {

    //@Autowired
    //private UsersRepo usersRepo;

    @GetMapping("/") //при переходе на главную страницу будет обрабатываться нижеуказанная функция
    public String home(Model model) {
        //model.addAttribute("title","Главная страница");  //параметр 'title' со значением 'Главная страница'
        CouplesDataService couplesDataService = new CouplesDataService();
        List<Couples> list = couplesDataService.testCouplesRepo();

        return "home"; //возвращает шаблон
    }




}
