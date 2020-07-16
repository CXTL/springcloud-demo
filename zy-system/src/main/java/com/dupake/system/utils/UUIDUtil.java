package com.dupake.system.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String genId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
