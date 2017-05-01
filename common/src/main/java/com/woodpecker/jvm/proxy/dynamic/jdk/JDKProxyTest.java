package com.woodpecker.jvm.proxy.dynamic.jdk;

import java.lang.reflect.Proxy;

/**
 * JDK动态代理测试类：动态代理实现实际是双层的静态代理
 *
 * @author Glenn
 * @since 2017-04-12
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        DefinedHandler definedHandler = new DefinedHandler();
        // 加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        /*
         * 新建了一个代理对象，实际代理类就是在这时候动态生成的。并且也实现了ProxyInterface接口
         */
        ProxyInterface proxy = (ProxyInterface) (Proxy.newProxyInstance(ProxyInterface.class.getClassLoader(), new Class[] {ProxyInterface.class},
                definedHandler));
        //ProxyClassSaver.saveProxyClass("/Users/idiom/Downloads", "$Proxy0", new Class[]{ProxyInterface.class});
        proxy.grandFather();
        proxy.grandMother();
        proxy.father();
    }
}
