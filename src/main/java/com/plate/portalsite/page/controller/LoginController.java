package com.plate.portalsite.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){

        System.out.println("asdasdas");
        return "admin/index";
    }
}
