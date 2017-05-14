package com.woodpecker.jvm.proxy.dynamic.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 保存动态生成的代理类
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public class ProxyClassSaver {
    /**
     * 保存代理类到指定目录
     *
     * @param path 指定目录
     * @param proxyClassName 代理类名称
     * @param interfaces 代理类实现接口
     * @return true-成功 false-失败
     */
    public static boolean saveProxyClass(String path, String proxyClassName, Class[] interfaces) {
        if (proxyClassName == null || path == null) {
            return false;
        }

        byte[] classFile = ProxyGenerator.generateProxyClass(proxyClassName, interfaces);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
