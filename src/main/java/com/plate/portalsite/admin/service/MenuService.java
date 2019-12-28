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

import java.util.*;

@Service
public class MenuService {

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
                HashMap<String, Object> item = new LinkedHashMap<>();
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
            if(parent.getId().equals(menuItem.getParentId())){
                HashMap<String, Object> item = new LinkedHashMap<>();
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

    /**
     * 获取当前树节点下的所有子节点 分页展示 按照code排序
     * @param parentId
     * @return
     */
    public List<Map<String, Object>> getTreeList(String parentId) {

        List<Map<String, Object>>result = new ArrayList<Map<String, Object>>();
        if(StringUtils.isEmpty(parentId)){
            return result;
        }


        List<MenuItem> list = menuItemMapper.getMenuList(parentId);
        //title","code","enTitle","description","kind
        int i=1;
        for (MenuItem menuItem : list){

            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id",menuItem.getId());
            item.put("num",i);
            item.put("title",menuItem.getTitle());
            item.put("code",menuItem.getCode());
            item.put("enTitle",menuItem.getEnTitle());
            item.put("description",menuItem.getDescription());
            item.put("kind",menuItem.getKind());
            item.put("data",menuItem.getData());
            result.add(item);
            i++;
        }
        return result;
    }

    public List<Map<String, String>> getContentType(String typeNoticekind) {
        List<Map<String,String>>result= new ArrayList<>();
        List<MenuItem> data = menuItemMapper.getItemByType(typeNoticekind);
        for (MenuItem menuItem : data){

            Map<String,String>item = new HashMap<>();
            item.put("id",menuItem.getId());
            item.put("title",menuItem.getTitle());
            result.add(item);
        }
        return result;
    }

    public MenuItem getItemById(String id) {
        return menuItemMapper.getItemById(id);
    }
}
