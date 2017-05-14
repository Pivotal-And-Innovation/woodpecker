package com.woodpecker.util;

import org.apache.commons.codec.binary.Base64;

/**
 * 封装的Base64编码工具类
 *
 * @author Relax
 * @since 2017年03月30日
 */
public final class BASE64 {
    /**
     * 编码
     *
     * @param arg 待编码字符串
     * @return 编码结果
     */
    public static String encode(String arg) {
        String result = "";
        if (arg != null) {
            try {
                result = Base64.encodeBase64String(arg.getBytes("UTF-8"));
            } catch (Exception e) {
                Logger.error("BASE64 Encode failed.", e);
                throw new IllegalArgumentException(e);
            }
        }

        return result;
    }

    /**
     * 解码
     *
     * @param arg 待解码字符串
     * @return 解码结果
     */
    public static String decode(String arg) {
        String result = "";
        if (arg != null) {
            try {
                byte[] tmp = Base64.decodeBase64(arg);
                result = new String(tmp, "UTF-8");
            } catch (Exception e) {
                Logger.error("BASE64 Decode failed.", e);
                throw new IllegalArgumentException(e);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(encode("the"));
        System.out.println(decode("dGhl"));
    }

}
