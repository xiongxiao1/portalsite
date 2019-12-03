package com.plate.portalsite.page.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowordController {

    @RequestMapping("/hello")
    public void hello(){

        System.out.printf("admin/index");
    }
}
