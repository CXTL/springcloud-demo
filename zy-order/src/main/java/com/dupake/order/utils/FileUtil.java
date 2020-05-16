package com.dupake.order.utils;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/11 10:43
 * @description
 */
public class FileUtil {


    /***
     *判断文件类型是不是2003版本
     * @param
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 判断文件类型是不是2007版本
     *
     * @param
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


}
