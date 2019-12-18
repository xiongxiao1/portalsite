package com.plate.portalsite.admin.service;

import com.plate.portalsite.admin.dao.MenuContentMapper;
import com.plate.portalsite.common.entity.Attachment;
import com.plate.portalsite.common.entity.ItemContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuContentService {
    @Autowired
    private MenuContentMapper menuContentMapper;

    public void saveOrUpdate(ItemContent itemContent, Attachment attachment) {


    }
}
