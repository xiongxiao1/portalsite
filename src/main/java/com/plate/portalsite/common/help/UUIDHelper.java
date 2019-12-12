package com.plate.portalsite.common.help;
import java.util.UUID;

public class UUIDHelper {


    public static synchronized String newUUID(){

        return  UUID.randomUUID().toString().replaceAll("-","");
    }
}
