package ru.geekbrains.spring1.inetch.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

}
