package com.plate.portalsite.admin.service;

import com.plate.portalsite.admin.dao.MenuItemMapper;
import com.plate.portalsite.common.entity.MenuItem;
import com.plate.portalsite.common.help.DateHelper;
import com.plate.portalsite.common.help.EvnHelper;
import com.plate.portalsite.common.help.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {


    static String ROOTID = "00000000000000000000000000000000";
    @Autowired
    private  MenuItemMapper menuItemMapper;

    public List<Map<String,Object>> getMenuTree(String parentId){

        ArrayList<Map<String, Object>> result = new ArrayList<>();
        createTree(result,this.getAllItem(parentId));
        return result;
    }

    /**
     *
     * @param result
     * @param data
     */
    private void createTree(List<Map<String, Object>> result,List<MenuItem> data){

        for (MenuItem menuItem : data){

            if( StringUtils.isEmpty(menuItem.getParentId()) ){
                HashMap<String, Object> item = new HashMap<>();
                item.put("id",menuItem.getId());
                item.put("title",menuItem.getTitle());
                item.put("children", createTreeList(menuItem,data));
                result.add(item);
            }

        }

    }

    public List<Map<String,Object>> createTreeList(MenuItem parent,List<MenuItem> data){

        List<Map<String,Object>> result = new ArrayList<>();

        for (MenuItem menuItem: data){
            if(menuItem.getParentId().equals(parent.getId())){
                HashMap<String, Object> item = new HashMap<>();
                item.put("id",menuItem.getId());
                item.put("title",menuItem.getTitle());
                item.put("children", createTreeList(menuItem,data));
                result.add(item);
            }
        }
        return result;
    }

    public List<MenuItem> getAllItem(String parentId){

        return menuItemMapper.getAllItem(parentId);
    }

    public void saveOrUpdate(MenuItem menuItem){

        if(StringUtils.isEmpty(menuItem.getId())){
            menuItem.setId(UUIDHelper.newUUID());
            menuItem.setCreatetime(DateHelper.getCurrDateTime());
            menuItem.setCreateuserid(EvnHelper.getCurrCurrLoginId());
            menuItemMapper.save(menuItem);
        }else{
            menuItem.setUpdatetime(DateHelper.getCurrDateTime());
            menuItem.setUpdateuserid(EvnHelper.getCurrCurrLoginId());
            menuItemMapper.update(menuItem);
        }

    }
}
