package com.plate.portalsite.admin.controller;

import com.plate.portalsite.admin.service.MenuContentService;
import com.plate.portalsite.common.entity.Attachment;
import com.plate.portalsite.common.entity.ItemContent;
import com.plate.portalsite.common.help.DateHelper;
import com.plate.portalsite.common.help.UUIDHelper;
import com.plate.portalsite.common.util.CommonConst;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 消息通知 消息内容
 */
@RestController
public class MeneContentController {

    @Autowired
    private MenuContentService contentService;

    /**
     * 发布
     * @param id
     */
    @RequestMapping("/publishMenuContent")
    public void publishMenuContent(String id,String pubFlag){
        contentService.publishMenuContent(id);
    }

    @RequestMapping("deleteItemContent")
    public void deleteItemContent(String itemContentId){
         contentService.deleteItemContent(itemContentId);
    }
    @RequestMapping("getItemContentPage")
    public Map<String,Object>getItemContentPage(Integer pageSize,Integer pageNumber,String itemId,Integer page,Integer rows){

        if (page != null && page != 0){
            pageNumber = page;
        }
        if(rows != null &&   rows != 0){
            pageSize = rows;
        }
        return contentService.getItemContentPageData(itemId, pageNumber, pageSize);
    }

    @RequestMapping("/getItemContentList")
    public List<Map<String,Object>> getItemContentList(String itemTypeId, String title, String dateStart, String dateEnd, int pageSize, int pageNum){

        return  contentService.getItemContentList(itemTypeId,title,dateStart,dateEnd,pageSize,pageNum);
    }

    @RequestMapping("/saveMenuContent")
    public void saveMenuContent(ItemContent itemContent, @RequestParam(value="titleFile") MultipartFile titleFile){


        //存储文件
        Attachment attachment = null;
        if(!StringUtils.isEmpty(titleFile.getOriginalFilename())){
            try {
                attachment= getAttachment(titleFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //保存消息内容数据
        contentService.saveOrUpdate(itemContent,attachment);
    }

    private Attachment getAttachment(@RequestParam("titleFile") MultipartFile titleFile) throws IOException {
        String fileName = titleFile.getOriginalFilename();
        //文件后缀

        String fix = fileName.substring(fileName.indexOf("."), fileName.length());
        String newFileName = UUIDHelper.newUUID() + fix;
        Attachment attachment = new Attachment();
        attachment.setRealname(fileName);
        attachment.setServername(newFileName);
        attachment.setPath(CommonConst.filePath + DateHelper.getCurrDateStr());
        File file = new File(CommonConst.filePath + DateHelper.getCurrDateStr());
        if(!file.exists()){
            file.mkdir();
        }
        File dest = new File(file, newFileName);
        FileUtils.copyInputStreamToFile(titleFile.getInputStream(),dest);
        return attachment;
    }
}
