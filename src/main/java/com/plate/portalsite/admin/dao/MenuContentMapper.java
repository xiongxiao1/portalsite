package com.plate.portalsite.admin.dao;

import com.plate.portalsite.common.entity.ItemContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuContentMapper {

    void save(ItemContent itemContent);

    void update(ItemContent itemContent);

    List<ItemContent> getItemContentList(Map<String, Object> params);

    List<ItemContent> getItemContentPage(@Param("itemId") String itemId, @Param("startIndex")int startIndex,  @Param("endIndex")int endIndex);
}
