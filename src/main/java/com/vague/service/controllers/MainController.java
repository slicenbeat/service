package com.vague.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //отвечает за обработку всех переходов на сайт
public class MainController {

    @GetMapping("/") //при переходе на главную страницу будет обрабатываться нижеуказанная функция
    public String home(Model model) {
        model.addAttribute("title","Главная страница");
        return "home"; //возвращает шаблон
    }

}
