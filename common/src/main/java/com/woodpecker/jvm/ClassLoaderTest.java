package com.woodpecker.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与instanceof关键字演示
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        /*构建一个类加载器*/
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object ob = myLoader.loadClass("com.beebank.riskmanage.jvm.ClassLoaderTest").newInstance();
        System.out.println(ob.getClass());
        System.out.println(ob instanceof ClassLoaderTest);
    }

}
