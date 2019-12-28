package com.plate.portalsite.common.entity;

import java.util.List;

/**
 * 动态消息， 官方通知，红头文件
 */
public class ItemContent {

    private String id;
    private String title;
    private String createdate;
    private String publishdate;
    private String createId;
    private String publishId;
    private String pubFlag;
    private String picId;
    private String orgId;
    private String topflag;
    private String topCreateTime;
    private String data;
    private String itemId;

    private String itemName;

    private List<Attachment> atts;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTopflag() {
        return topflag;
    }

    public void setTopflag(String topflag) {
        this.topflag = topflag;
    }

    public String getTopCreateTime() {
        return topCreateTime;
    }

    public void setTopCreateTime(String topCreateTime) {
        this.topCreateTime = topCreateTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPubFlag() {
        return pubFlag;
    }

    public void setPubFlag(String pubFlag) {
        this.pubFlag = pubFlag;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<Attachment> getAtts() {
        return atts;
    }

    public void setAtts(List<Attachment> atts) {
        this.atts = atts;
    }
}
