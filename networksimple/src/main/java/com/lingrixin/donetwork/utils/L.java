package com.lingrixin.donetwork.utils;

import android.util.Log;

import com.lingrixin.donetwork.BuildConfig;

/**
 * Created by LRXx open 2017-3-31.
 */

public class L {

    private static boolean open = true;//全局Log开关

    /**
     * 提供一个全局Log开关，默认开启
     *
     * @param open
     */
    public static void setOpen(boolean open) {
        L.open = open;
    }

    private static final String LOG_PREFIX = "NB_";//程序Log固定前缀
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();//前缀长度
    private static final int MAX_LOG_TAG_LENGTH = 23;//最大前缀数

    /**
     * 提供一个加缀方法,如果大于最大前缀-固定前缀,则斩去后面的尾巴
     *
     * @param str
     * @return
     */
    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return LOG_PREFIX + str;
    }

    /**
     * 给每个类,提供日志前缀
     *
     * @param cls
     * @return
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    /**
     * verbose 类信息,只有在debug版本时打印
     *
     * @param tag
     * @param messages
     */
    public static void v(String tag, Object... messages) {
        if (BuildConfig.DEBUG) {
            log(tag, Log.VERBOSE, null, messages);
        }
    }

    /**
     * debug 类信息,只有在debug版本时打印
     *
     * @param tag
     * @param messages
     */
    public static void d(String tag, Object... messages) {
        if (BuildConfig.DEBUG) {
            log(tag, Log.DEBUG, null, messages);
        }
    }

    /**
     * info 类信息,直接处理
     *
     * @param tag
     * @param messages
     */
    public static void i(String tag, Object... messages) {
        log(tag, Log.INFO, null, messages);
    }

    /**
     * warn 类信息,直接处理
     *
     * @param tag
     * @param messages
     */
    public static void w(String tag, Object... messages) {
        log(tag, Log.WARN, null, messages);
    }

    /**
     * warn 类信息 可以附带异常信息
     *
     * @param tag
     * @param messages
     */
    public static void w(String tag, Throwable t, Object... messages) {
        log(tag, Log.WARN, t, messages);
    }

    /**
     * error 类信息,直接处理
     *
     * @param tag
     * @param messages
     */
    public static void e(String tag, Object... messages) {
        log(tag, Log.ERROR, null, messages);
    }

    /**
     * error 类信息,可以附带异常信息
     *
     * @param tag
     * @param t
     * @param messages
     */
    public static void e(String tag, Throwable t, Object... messages) {
        log(tag, Log.ERROR, t, messages);
    }

    /**
     * Log核心处理方法,标签 + 等级 + 异常 + 信息
     *
     * @param tag
     * @param level
     * @param t
     * @param messages
     */
    private static void log(String tag, int level, Throwable t, Object... messages) {
        if (open && Log.isLoggable(tag, level)) {
            String message;
            if (t == null && messages != null && messages.length == 1) {
                message = messages[0].toString();
            } else {
                StringBuilder sb = new StringBuilder();
                if (messages != null) for (Object m : messages) {
                    sb.append(m);
                }
                if (t != null) {
                    sb.append("\n").append(Log.getStackTraceString(t));
                }
                message = sb.toString();
            }
            Log.println(level, tag, message);
        }
    }
}
