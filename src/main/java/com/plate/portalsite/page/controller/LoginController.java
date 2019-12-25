package com.plate.portalsite.page.controller;

import com.plate.portalsite.admin.service.MenuService;
import com.plate.portalsite.common.entity.MenuItem;
import com.plate.portalsite.common.util.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private MenuService menuService;

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
    @RequestMapping("/contentList")
    public String contentList(Model model){
        return "admin/contentList";
    }
    @RequestMapping("/index")
    public String csdaPage(HttpServletRequest request){

        List<Map<String, Object>> allItem = menuService.getMenuTree(CommonConst.rootId);
//
//        mv.addObject("allItem",allItem);
        request.setAttribute("allItem", allItem);
        return "csda";
    }
}