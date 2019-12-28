package com.plate.portalsite.admin.controller;

import org.springframework.util.StringUtils;
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
        String[] fieldCols = null;
        String[] titleCols = null;
        if(!StringUtils.isEmpty(type)){
            if(type.equals("item")) {
                fieldCols = new String[]{"num", "id","data", "title", "code", "enTitle", "description", "kind"};
                titleCols = new String[]{"序号", "id","data", "标题", "编码", "英文标题", "描述", "类型"};
                //表格
                createTableCols(result, fieldCols, titleCols);
                //表单
                createFormCols(result, fieldCols, titleCols);
            }
           if(type.equals("content")){
               fieldCols = new String[]{"num", "id", "title", "pubFlag", "publishdate",  "itemName",};
               titleCols = new String[]{"序号", "id", "标题", "是否发布", "发布日期",  "消息类型",};
               //表格
               createTableCols(result, fieldCols, titleCols);
           }
        }

        result.put("total",1000);
        result.put("size",10);
        return result;
    }

    private void createFormCols(Map<String, Object> result, String[] fieldCols, String[] titleCols) {
        List<Map<String,Object>> formCols = new ArrayList<>();
        for (int i=1;i<fieldCols.length;i++) {
            LinkedHashMap<String, Object> item = new LinkedHashMap<>();
            item.put("field",fieldCols[i]);
            item.put("title",titleCols[i]);
            item.put("width",300);
            if(i==1 || i==2)
                item.put("hidden",true);
            else
                item.put("hidden",false);
            formCols.add(item);
        }
        result.put("formCols",formCols);
    }

    private void createTableCols(Map<String, Object> result, String[] fieldCols, String[] titleCols) {
        List<Map<String,Object>> tableCols = new ArrayList<>();
        for (int i=0;i<fieldCols.length;i++) {
            LinkedHashMap<String, Object> item = new LinkedHashMap<>();
            item.put("field",fieldCols[i]);
            item.put("title",titleCols[i]);
            item.put("width",100);
            if( i==1 || i==2){
                item.put("hidden",true);
            }
            tableCols.add(item);
        }
        result.put("tableCols",tableCols);
    }
}
