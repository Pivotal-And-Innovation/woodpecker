package com.woodpecker.util;

import org.apache.commons.codec.binary.Base64;

/**
 * 封装的Base64加解密工具类
 *
 * @author Glenn
 * @since 2017-03-27
 */
public final class BASE64 {
    /**
     * 加密
     *
     * @param arg 待加密字符串
     * @return 加密结果
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
     * 解密
     *
     * @param arg 待解密字符串
     * @return 解密结果
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

}
