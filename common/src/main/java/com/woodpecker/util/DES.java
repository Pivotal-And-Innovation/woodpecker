package com.woodpecker.util;

import com.woodpecker.common.CommonPropertyFactory;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加解密工具类
 *
 * @author Relax
 * @since 2017年03月30日
 */
public final class DES {
    /**
     * 加密
     *
     * @param arg 待加密字符串
     * @return 加密结果
     * @throws IllegalArgumentException 加密失败
     */
    public static String encrypt(String arg) {
        try {
            byte[] raw = CommonPropertyFactory.getProperty("aesKey", "").getBytes("UTF-8");
            SecretKeySpec sks = new SecretKeySpec(raw, "DES");
            // "算法/模式/补码方式" ECB
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv1 = new IvParameterSpec(CommonPropertyFactory.getProperty("aesIv", "").getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, sks, iv1);
            byte[] encrypted = cipher.doFinal(arg.getBytes("utf-8"));

            // 处使用BASE64做转码功能，同时能起到2次加密功能
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new IllegalArgumentException("DES Encrypt failed, please recheck the argument.");
        }
    }

    /**
     * 解密
     *
     * @param arg 待解密字符串
     * @return 解密结果
     * @throws IllegalArgumentException 解密失败
     */
    public static String decrypt(String arg) {
        try {
            byte[] raw = CommonPropertyFactory.getProperty("aesKey", "").getBytes("UTF-8");
            SecretKeySpec sks = new SecretKeySpec(raw, "DES");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv1 = new IvParameterSpec(CommonPropertyFactory.getProperty("aesIv", "").getBytes());
            cipher.init(Cipher.DECRYPT_MODE, sks, iv1);
            // 先用base64解密
            byte[] encrypted1 = Base64.getDecoder().decode(arg);
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, "utf-8");
        } catch (IllegalBlockSizeException e) {
            return arg;
        } catch (Exception e) {
            throw new IllegalArgumentException("DES Decrypt failed, please recheck the argument.");
        }
    }

    public static void main(String[] args) {
        System.out.println(encrypt("tyd"));
    }

}
