package com.woodpecker.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * 报警日志工具类
 *
 * @author Glenn
 * @since 2017-03-27
 */
public final class AlarmLogger {

    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger("woodpecker_error");
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 记录警告日志
     *
     * @param msg 日志信息
     * @param appendMsg 日志拼接信息
     */
    public static void warn(Object msg, String appendMsg) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // Date Time
        String date = DATE_FORMAT.format(new Date());
        // log level
        String level = "[JAPP_NAME:";
        // client IP, default 127.0.0.1
        String client_ip = "127.0.0.1";
        // uri of the request
        String uri = "";
        if (request != null) {
            client_ip = getIpAddress(request);
            uri = request.getRequestURI();
        }

        // serial ID of the request, default '000000'
        String control_name = "000000";
        // extra message
        String extra = uri;
        extra = StringUtils.remove(extra, '\n');

        try {
            MDC.put("level", level);
            MDC.put("projectName", "spider-man");
            MDC.put("date", date);
            MDC.put("server_ip", InetAddress.getLocalHost().getHostAddress());
            MDC.put("client_ip", client_ip);
            MDC.put("control_name", control_name);
            MDC.put("ret_message", msg.toString() + " " + extra);
            MDC.put("appendMsg", appendMsg);
            LOGGER.error(msg.toString() + " | " + appendMsg);
        } catch (Exception e) {
            LOGGER.error("多线程调用...MDC put操作时出错...");
        } finally {
            MDC.clear();
        }
    }

    /**
     * 跨过ngix获取服务器IP
     *
     * @param request 请求
     * @return IP地址
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String[] ips = ip.split(",");
        return ips[0];
    }

}
