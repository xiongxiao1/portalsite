package com.plate.portalsite.admin.dao;

import com.plate.portalsite.common.entity.SysInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysInfoMapper {

    SysInfo getByCode(@Param("syscode") String code);

    SysInfo getById(@Param("id") String id);

    void save(SysInfo sysInfo);

    void update(SysInfo sysInfo);

    void deleteById(String id);

    List<SysInfo> getSysInfoPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    int getPageTotal();
}
