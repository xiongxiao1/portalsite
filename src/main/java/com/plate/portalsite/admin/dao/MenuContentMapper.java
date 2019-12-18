package com.plate.portalsite.admin.dao;

import com.plate.portalsite.common.entity.ItemContent;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuContentMapper {

    void save(ItemContent itemContent);

    void update(ItemContent itemContent);
}
