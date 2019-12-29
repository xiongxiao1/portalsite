package com.plate.portalsite.admin.service;

import com.plate.portalsite.admin.dao.AttachmentMapper;
import com.plate.portalsite.admin.dao.MenuContentMapper;
import com.plate.portalsite.common.entity.Attachment;
import com.plate.portalsite.common.entity.ItemContent;
import com.plate.portalsite.common.entity.MenuItem;
import com.plate.portalsite.common.help.DateHelper;
import com.plate.portalsite.common.help.EvnHelper;
import com.plate.portalsite.common.help.UUIDHelper;
import com.plate.portalsite.common.util.CommonConst;
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
            if(attachment != null){
                attachment.setId(UUIDHelper.newUUID());
                attachment.setOwnId(itemContent.getId());
                attachment.setDeleteFlag("0");
                attachmentMapper.save(attachment);
            }
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
        int i=1;
        for (ItemContent itemContent:
        list) {

            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("num",i++);
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

    public List<ItemContent> getItemContentPage(String itemId, int pageNum, int pageSize) {

        int startIndex =(pageNum-1)*pageSize;
        List<ItemContent>list = menuContentMapper.getItemContentPage(itemId,startIndex,pageSize);
        return list;
    }

    public ItemContent getItemContentById(String id) {

        return menuContentMapper.getItemContentById(id);
    }

    public Map<String, Object> getItemContentPageData(String itemId, int pageNum, int pageSize) {


        Map<String, Object> result = new HashMap<>();

        List<ItemContent> itemContentPage = this.getItemContentPage(itemId, pageNum, pageSize);
        long total  = this.getPageCount(itemId);
        result.put("itemContents",itemContentPage);
        result.put("totalCount",total);
        result.put("total", total);
        List<Map<String,Object>>rows = new ArrayList<>();
        int i=1;
        for (ItemContent ItemContent :itemContentPage){

            HashMap<String, Object> item = new HashMap<>();
            item.put("num",i++);
            item.put("id",ItemContent.getId());
            item.put("title",ItemContent.getTitle());
            item.put("data",ItemContent.getData());
            item.put("kindId",ItemContent.getItemId());
            item.put("kind",ItemContent.getItemName());
            item.put("pubFlag",ItemContent.getPubFlag());
            item.put("pubdate",ItemContent.getPublishdate());
            rows.add(item);
        }
        result.put("rows", rows);
        return result;
    }

    private long getPageCount(String itemId) {

        return menuContentMapper.getItemContentPageContent(itemId);
    }

    /**
     * 删除消息 并删除附件
     * @param itemContentId
     */
    public void deleteItemContent(String itemContentId) {
        ItemContent itemContent = menuContentMapper.getItemContentById(itemContentId);

        if(itemContent != null){

            menuContentMapper.deleteById(itemContent.getId());
            attachmentMapper.deleteByOwnId(itemContent.getId());
        }
    }

    public void publishMenuContent(String id) {
        ItemContent itemContentById = menuContentMapper.getItemContentById(id);
        itemContentById.setPubFlag(CommonConst.YES);
        itemContentById.setPublishdate(DateHelper.getCurrDateTime());
        this.saveOrUpdate(itemContentById,null);
    }
}
