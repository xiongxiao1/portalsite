package com.plate.portalsite.admin.controller;

import com.plate.portalsite.admin.service.MenuContentService;
import com.plate.portalsite.common.entity.Attachment;
import com.plate.portalsite.common.entity.ItemContent;
import com.plate.portalsite.common.help.DateHelper;
import com.plate.portalsite.common.help.UUIDHelper;
import com.plate.portalsite.common.util.CommonConst;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 消息通知 消息内容
 */
@RestController
public class MeneContentController {

    @Autowired
    private MenuContentService senuContentService;
    @RequestMapping("/saveMenuContent")
    public void saveMenuContent(ItemContent itemContent, @RequestParam(value="titleFile") MultipartFile titleFile){

        //存储文件
        Attachment attachment = null;

        try {
            attachment= getAttachment(titleFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //保存消息内容数据
        senuContentService.saveOrUpdate(itemContent,attachment);
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
