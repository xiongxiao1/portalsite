package com.plate.portalsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.plate.portalsite.admin.dao")
public class PortalsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalsiteApplication.class, args);
    }

}
