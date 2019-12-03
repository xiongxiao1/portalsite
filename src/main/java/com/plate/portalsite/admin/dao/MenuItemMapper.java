package com.plate.portalsite.admin.dao;

import com.plate.portalsite.common.entity.MenuItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuItemMapper {

    public List<MenuItem> getAllItem(String parentId);
}
