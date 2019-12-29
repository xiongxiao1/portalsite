package com.plate.portalsite.admin.dao;

import com.plate.portalsite.common.entity.MenuItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuItemMapper {

    List<MenuItem> getAllItem(String parentId);

    void save(MenuItem menuItem);

    void update(MenuItem menuItem);

    List<MenuItem> getMenuList(String parentId);

    List<MenuItem> getItemByType(String kind);

    MenuItem getItemById(String id);

    MenuItem getItemByCode(@Param("code") String code);
}
