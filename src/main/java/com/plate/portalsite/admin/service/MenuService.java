package com.plate.portalsite.admin.service;

import com.plate.portalsite.admin.dao.MenuItemMapper;
import com.plate.portalsite.common.entity.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private  MenuItemMapper menuItemMapper;

    public List<MenuItem> getAllItem(String parentId){

        return menuItemMapper.getAllItem(parentId);
    }
}
