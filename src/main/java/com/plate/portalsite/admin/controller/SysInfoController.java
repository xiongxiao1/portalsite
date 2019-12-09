package com.plate.portalsite.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SysInfoController {

    /**
     * 返回对应列 生成表格和表单
     * @param type
     * @return
     */
    @RequestMapping("getTabInfo")
    public Map<String, Object> getTabInfo(String type){

        Map<String,Object>result = new HashMap<>();

        String[] fieldCols  = {"num","title","enTitle","description","kind"};
        String[] titleCols  = {"序号","标题","英文标题","描述","类型"};
        //表格
        List<Map<String,Object>>tableCols = new ArrayList<>();
        for (int i=0;i<fieldCols.length;i++) {
            LinkedHashMap<String, Object> item = new LinkedHashMap<>();
            item.put("field",fieldCols[i]);
            item.put("title",titleCols[i]);
            item.put("width",100);
            if(i==0){
                item.put("hidden",true);
            }
            tableCols.add(item);
        }
        result.put("tableCols",tableCols);
        //表单
        List<Map<String,Object>>formCols = new ArrayList<>();
        for (int i=1;i<fieldCols.length;i++) {
            LinkedHashMap<String, Object> item = new LinkedHashMap<>();
            item.put("field",fieldCols[i]);
            item.put("title",titleCols[i]);
            item.put("width",100);
            if(i==0)
                item.put("hidden",true);
            else
                item.put("hidden",false);
            formCols.add(item);
        }
        result.put("formCols",formCols);
        return result;
    }
}
