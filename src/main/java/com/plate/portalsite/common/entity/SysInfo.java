package com.plate.portalsite.common.entity;

/**
 * 系统变量
 */
public class SysInfo {

    private String id;
    private String name;
    private String syscode;
    private String description;
    private String sysvalue;
    private String canDel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSysvalue() {
        return sysvalue;
    }

    public void setSysvalue(String sysvalue) {
        this.sysvalue = sysvalue;
    }

    public String getCanDel() {
        return canDel;
    }

    public void setCanDel(String canDel) {
        this.canDel = canDel;
    }
}
