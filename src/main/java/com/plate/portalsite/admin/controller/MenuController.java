package com.plate.portalsite.admin.controller;

import com.plate.portalsite.admin.service.MenuService;
import com.plate.portalsite.common.entity.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    //获取所有主页面tabs 的树节点
    @RequestMapping("/getAllItem")
    public List<Map<String,Object>> getAllItem(String parentId){

        return  menuService.getMenuTree(parentId);
    }

    //获取当前节点的所有子节点
    @RequestMapping("/getTreeList")
    public List<Map<String, Object>> getTreeList(String parentId){


        return menuService.getTreeList(parentId);
    }


    @RequestMapping("/saveOrUpdateMenuItem")
    public void saveMenuItem(MenuItem menuItem){

        if(StringUtils.isEmpty(menuItem.getParentId())){
            return;
        }
        menuService.saveOrUpdate(menuItem);
    }
}
