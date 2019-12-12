package com.plate.portalsite.admin.dao;

import com.plate.portalsite.common.entity.MenuItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuItemMapper {

    public List<MenuItem> getAllItem(String parentId);

    public void save(MenuItem menuItem);

    public void update(MenuItem menuItem);
}
