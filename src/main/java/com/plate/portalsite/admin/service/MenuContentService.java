package com.plate.portalsite.admin.service;

import com.plate.portalsite.admin.dao.AttachmentMapper;
import com.plate.portalsite.admin.dao.MenuContentMapper;
import com.plate.portalsite.common.entity.Attachment;
import com.plate.portalsite.common.entity.ItemContent;
import com.plate.portalsite.common.help.DateHelper;
import com.plate.portalsite.common.help.EvnHelper;
import com.plate.portalsite.common.help.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuContentService {
    @Autowired
    private MenuContentMapper menuContentMapper;
    @Autowired
    private AttachmentMapper attachmentMapper;

    public void saveOrUpdate(ItemContent itemContent, Attachment attachment) {


        if(StringUtils.isEmpty(itemContent.getId())){
            itemContent.setId(UUIDHelper.newUUID());
            itemContent.setCreatedate(DateHelper.getCurrDateTime());
            itemContent.setCreateId(EvnHelper.getCurrCurrLoginId());
            menuContentMapper.save(itemContent);
            //保存附件
            attachment.setId(UUIDHelper.newUUID());
            attachment.setOwnId(itemContent.getId());
            attachment.setDeleteFlag("0");
            attachmentMapper.save(attachment);
        }else{
            menuContentMapper.update(itemContent);
            if(attachment != null){

                attachmentMapper.deleteByOwnId(itemContent.getId());
                attachment.setOwnId(itemContent.getId());
                attachment.setDeleteFlag("0");
                attachmentMapper.save(attachment);
            }
        }
    }

    /**
     *
     * @param itemTypeId
     * @param title
     * @param dateStart
     * @param dateEnd
     * @param pageSize
     * @param pageNum
     * @return
     */
    public List<Map<String,Object>> getItemContentList(String itemTypeId, String title, String dateStart, String dateEnd, int pageSize, int pageNum) {

        Map<String,Object>params = new HashMap<>();
        params.put("itemTypeId",itemTypeId);
        params.put("title",title);
        params.put("dateStart",dateStart);
        params.put("dateEnd",dateEnd);
        params.put("pageStart",pageSize*(pageNum-1));
        params.put("pageEnd",pageSize*pageNum-1);
        List<ItemContent>list = menuContentMapper.getItemContentList(params);

        List<Map<String,Object>> result = new ArrayList<>();
        for (ItemContent itemContent:
        list) {

            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("id",itemContent.getId());
            stringObjectHashMap.put("title",itemContent.getTitle());
            stringObjectHashMap.put("data",itemContent.getData());
            stringObjectHashMap.put("pubFlag",itemContent.getPubFlag());
            stringObjectHashMap.put("itemId",itemContent.getItemId());
            stringObjectHashMap.put("itemName",itemContent.getItemName());
            stringObjectHashMap.put("publishDate",itemContent.getPublishdate());
            stringObjectHashMap.put("createDate",itemContent.getCreatedate());
            result.add(stringObjectHashMap);
        }
        return result;
    }
}
