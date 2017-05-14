package com.woodpecker.util;

import lombok.Cleanup;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 记录日志工具类
 *
 * @author Relax
 * @since 2017年03月30日
 */
@SuppressWarnings("unused")
public final class Logger {

    /**
     * 异常堆栈中类和方法的位置常量
     */
    private static final int CLASS_VAR = 3;
    private static final int METHOD_VAR = 4;

    /**
     * APIS {@code Logger Class} 提供的
     */
    public static void debug(Object msg) {
        org.slf4j.Logger logger = Logger.getLogger();
        logger.debug(Logger.getMsg(msg));
    }

    public static void info(Object msg) {
        org.slf4j.Logger logger = Logger.getLogger();
        logger.info(Logger.getMsg(msg));
    }

    public static void error(Object msg, Throwable exception) {
        org.slf4j.Logger logger = Logger.getLogger();
        logger.error(Logger.getMsg(msg, exception));
    }

    public static void warn(Object msg, Throwable exception) {
        org.slf4j.Logger logger = Logger.getLogger();
        logger.warn(Logger.getMsg(msg, exception));
    }

    public static void info(Object msg, Object o1) {
        org.slf4j.Logger logger = Logger.getLogger();
        logger.info(Logger.getMsg(msg), o1);
    }

    public static void info(Object msg, Object o1, Object o2) {
        org.slf4j.Logger logger = Logger.getLogger();
        logger.info(Logger.getMsg(msg), o1, o2);
    }

    public static void info(Object msg, Object o1, Object o2, Object o3) {
        org.slf4j.Logger logger = Logger.getLogger();
        logger.info(Logger.getMsg(msg), o1, o2, o3);
    }

    public static void info(Object msg, Object[] obj) {
        org.slf4j.Logger logger = Logger.getLogger();
        logger.info(Logger.getMsg(msg), obj);
    }

    public static void error(Object msg) {
        org.slf4j.Logger logger = Logger.getLogger();
        logger.error(Logger.getMsg(msg));
    }

    /**
     * 获取{@code org.slf4j.Logger}用来记录日志信息
     *
     * @return {@code org.slf4j.Logger}
     */
    private static org.slf4j.Logger getLogger() {
        return LoggerFactory.getLogger(Logger.getLogClass());
    }

    /**
     * 获取记录日志的 {@code Class}
     *
     * @return {@code Class}
     */
    private static String getLogClass() {
        String classStr = "";

        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (stack.length > CLASS_VAR) {
            StackTraceElement ste = stack[CLASS_VAR];
            classStr = ste.getClassName();
        }

        return classStr;
    }

    /**
     * 获取记录日志的 {@code Method}
     *
     * @return {@code Method}
     */
    private static String getLogMethod() {
        String methodStr = "";

        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (stack.length > METHOD_VAR) {
            StackTraceElement ste = stack[METHOD_VAR];
            methodStr = "Method[" + ste.getMethodName() + "]";
        }

        return methodStr;
    }

    /**
     * 获取日志信息
     *
     * @param msg 日志信息
     * @return 日志信息
     */
    private static String getMsg(Object msg) {
        return Logger.getMsg(msg, null);
    }

    /**
     * 获取完整异常日志信息
     *
     * @param msg 日志信息
     * @param exception {@code Exception}
     * @return 完整异常信息
     */
    private static String getMsg(Object msg, Throwable exception) {
        String str = "";

        if (exception == null) {
            str = Logger.getLogMethod() + " message:[" + msg.toString() + "]";
        }

        if (exception != null) {
            str = Logger.getLogMethod() + " message: [" + msg.toString() + "]";
            if (StringUtils.isEmpty(exception.getMessage())) {
                str += " errorStackTrace: [" + getExceptionStack(exception) + "]";
            }else {
                str += " errorMessage: [" + exception.getMessage().replaceAll("[\\n\\r]", "|") + "]";
                str += " errorStackTrace: [" + getExceptionStack(exception) + "]";
            }
        }

        return str;
    }

    /**
     * 获取 {@code Exception} 堆栈信息
     *
     * @return {@code Exception}堆栈信息
     */
    private static String getExceptionStack(Throwable exception) {
        String var = "@";
        try {
            @Cleanup
            StringWriter sw = new StringWriter();
            @Cleanup
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString().replaceAll("[\\n\\r]", var);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
