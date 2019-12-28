package com.plate.portalsite.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MyConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.ofKilobytes(2000)); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofKilobytes(2000));
        return factory.createMultipartConfig();
    }
}
