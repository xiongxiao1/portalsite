package com.plate.portalsite.admin.controller;

import com.plate.portalsite.admin.service.MenuService;
import com.plate.portalsite.common.entity.MenuItem;
import com.plate.portalsite.common.util.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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
//        if(!StringUtils.isEmpty(menuItem.getData()))
//            menuItem.setData(menuItem.getData().substring(1,menuItem.getData().length()));
        menuService.saveOrUpdate(menuItem);
    }

    /**
     * 获取导航栏类型 或者 消息类型
     * @param type
     * @return
     */
    @RequestMapping("/getContentType")
    public List<Map<String,String>> getContentType(String type){

        if("content".equals(type)){

            List<Map<String,String>>result= menuService.getContentType(CommonConst.TYPE_NOTICEKIND);
            return result;
        }else if("menu".equals(type)){
            List<Map<String,String>>result= new ArrayList<>();
            HashMap<String, String> item1 = new HashMap<>();
            item1.put("id",CommonConst.TYPE_NOTICEKIND);
            item1.put("title","消息型导航栏");
            result.add(item1);

            HashMap<String, String> item2 = new HashMap<>();
            item2.put("id",CommonConst.TYPE_INDEX);
            item2.put("title","首页导航栏");
            result.add(item2);

            HashMap<String, String> item3 = new HashMap<>();
            item3.put("id",CommonConst.TYPE_INDEX);
            item3.put("title","自有内容导航栏");
            result.add(item3);
            return result;
        }
        return null;
    }
}
