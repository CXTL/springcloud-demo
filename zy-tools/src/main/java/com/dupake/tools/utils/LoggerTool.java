/**
 * @ClassName: LoggerTool
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2019-07-01 10:18
 * @version 1.0
 */

package com.dupake.tools.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LoggerTool {

    private static Map<String, Logger> loggerMap = new HashMap<String, Logger>();

    /**
     * 获取最开始的调用者所在类
     */
    private static String getClassName() {
        Throwable th = new Throwable();
        StackTraceElement[] stes = th.getStackTrace();
        StackTraceElement ste = stes[2];
        return ste.getClassName();
    }

    /**
     * 根据类名获得logger对象
     */
    private static Logger getLogger(String className) {
        Logger logger = null;
        if (loggerMap.containsKey(className)) {
            logger = loggerMap.get(className);
        } else {
            try {
                logger = LoggerFactory.getLogger(Class.forName(className));
                loggerMap.put(className, logger);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return logger;
    }

    // -----------------------------debug

    public static void debug(String message) {
        debug(message, null);
    }

    public static void debug(String message, Object... args) {
        getLogger(getClassName()).debug(message, args);
    }

    public static void debug(Throwable cause, String message) {
        getLogger(getClassName()).debug(message, cause);
    }

    // -----------------------------debug

    public static void info(String message) {
        info(message, null);
    }

    public static void info(String message, Object... args) {
        getLogger(getClassName()).info(message, args);
    }

    public static void info(Throwable cause, String message) {
        getLogger(getClassName()).info(message, cause);
    }

    // -----------------------------warning

    public static void warn(String message) {
        warn(message, null);
    }

    public static void warn(String message, Object... args) {
        getLogger(getClassName()).warn(message, args);
    }

    public static void warn(Throwable cause, String message) {
        getLogger(getClassName()).warn(message, cause);
    }

    // -----------------------------error

    public static void error(String message) {
        error(message, null);
    }

    public static void error(String message, Object... args) {
        getLogger(getClassName()).error(message, args);
    }

    public static void error(Throwable cause, String message) {
        getLogger(getClassName()).error(message, cause);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        LoggerTool.debug("这是Test");
        LoggerTool.debug("这是Test a={}, b={}", 1, 2);
    }

}
