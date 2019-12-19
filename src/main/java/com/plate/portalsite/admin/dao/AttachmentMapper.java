package com.plate.portalsite.admin.dao;

import com.plate.portalsite.common.entity.Attachment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentMapper {

    List<Attachment> getAttachmentsByOwnId(String ownId);

    void save(Attachment attachment);

    void update(Attachment attachment);

    void deleteByOwnId(String ownId);
}
