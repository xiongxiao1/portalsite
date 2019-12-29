package com.plate.portalsite.page.controller;

import com.plate.portalsite.admin.dao.SysInfoMapper;
import com.plate.portalsite.admin.service.MenuContentService;
import com.plate.portalsite.admin.service.MenuService;
import com.plate.portalsite.common.entity.ItemContent;
import com.plate.portalsite.common.entity.MenuItem;
import com.plate.portalsite.common.util.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuContentService menuContentService;
    @Autowired
    private SysInfoMapper sysInfoMapper;
    @RequestMapping("/login")
    public String login() {

        System.out.println("asdasdas");
        return "admin/index";
    }

    @RequestMapping("sysInfoList")
    public String sysInfoList(){

        return "admin/sysInfoList";
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

    String getDefaultItemValue(String key){
        try{
            return sysInfoMapper.getByCode(key).getSysvalue();
        }catch (Exception e){
            System.out.printf("******没有设置系统变量*****"+key);
            System.out.printf("******没有设置系统变量*****"+key);
            System.out.printf("******没有设置系统变量*****"+key);
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping(value ={"/index/{id}","/index"} )
    public String csdaPage(HttpServletRequest request, @PathVariable(value="id",required=false) String id, String itemcontentId){

        List<Map<String, Object>> allItem = menuService.getMenuTree(CommonConst.rootId);

        String data = "";
        int isFirstPage = 0;
        MenuItem item = null;

        if(!StringUtils.isEmpty(id)){

            item = menuService.getItemById(id);
            data = item.getData();
            if (!"01".equals(item.getCode()))
                isFirstPage = 1;
        }else{
            item = menuService.getItemByCode(getDefaultItemValue("mrdhl"));
        }

        //做详情页处理
        if(!StringUtils.isEmpty(itemcontentId)){
            isFirstPage = 2;
        }
        HashMap<String, Object> result = new HashMap<>();
        int pageNum = 1;
        int pageSize = 6;
        if(isFirstPage == 0){ //首页

            //获取最新动态6 官方通知 7个，红头文件 10个，
            MenuItem wyhdt = menuService.getItemByCode(getDefaultItemValue("sydhl1"));//官方通知

            List<ItemContent> wyhdts =menuContentService.getItemContentPage(wyhdt.getId(),pageNum,pageSize);
            result.put("wyhdts",wyhdts);
            result.put("wyhdt",wyhdt);

            pageSize = 7;
            MenuItem notice = menuService.getItemByCode(getDefaultItemValue("sydhl2"));//官方通知
            List<ItemContent> notices =menuContentService.getItemContentPage(notice.getId(),pageNum,pageSize);
            result.put("notices",notices);
            result.put("notice",notice);

            MenuItem htwj = menuService.getItemByCode(getDefaultItemValue("sydhl3"));//红头文件
            pageSize = 10;
            List<ItemContent> htwjs =menuContentService.getItemContentPage(notice.getId(),pageNum,pageSize);
            result.put("htwjs",htwjs);
            result.put("htwj",htwj);

        }else if(isFirstPage == 1){//菜单自带内容页
            request.setAttribute("menuData", data);

        }else if(isFirstPage == 2){//内容详细页

            ItemContent menuContent = menuContentService.getItemContentById(itemcontentId);
            request.setAttribute("menuContentData", menuContent.getData());
        }

        request.setAttribute("currItem",item);
        request.setAttribute("result", result);
        request.setAttribute("isFirstPage", isFirstPage);
        request.setAttribute("allItem", allItem);
        return "csda";
    }
}