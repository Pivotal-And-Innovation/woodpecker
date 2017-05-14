package com.woodpecker.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类：单向散列加密，可添加slot增强加密
 *
 * @author Relax
 * @since 2017年03月30日
 */
public final class MD5 {

    /**
     * 全局数组
     */
    private final static String[] STR_DIGIT = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    /**
     * MD5加密
     *
     * @param arg 待加密字符串
     * @return 加密结果字符串
     */
    public static String encrypt(String arg) {
        String result = arg;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest()该函数返回值为存放哈希值结果的byte数组
            result = byteToString(md.digest(arg.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.error("Get MD5 encoding string IN ERROR. Reason:", ex);
        }

        return result;
    }

    /*转换字节数组为16进制字串*/
    private static String byteToString(byte[] arg) {
        StringBuilder sb = new StringBuilder();
        for (byte item : arg) {
            sb.append(byteToArrayString(item));
        }

        return sb.toString();
    }

    /*返回形式为数字跟字符串*/
    private static String byteToArrayString(byte param) {
        int iRet = param;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return STR_DIGIT[iD1] + STR_DIGIT[iD2];
    }

}
