package com.plate.portalsite.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {

        System.out.println("asdasdas");
        return "admin/index";
    }

    @RequestMapping("/itemPag")
    public String itemPag(String rootId, Model model) {

        model.addAttribute("rootId", rootId);
        return "admin/itemPage";
    }

    @RequestMapping("/itemEditor")
    public String itemEditor(Model model) {
        return "admin/itemEditor";
    }
}