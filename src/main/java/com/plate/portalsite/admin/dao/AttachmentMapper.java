package com.plate.portalsite.admin.dao;

import com.plate.portalsite.common.entity.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentMapper {

    void save(Attachment itemContent);

    void update(Attachment itemContent);
}
