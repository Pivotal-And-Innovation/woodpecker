package com.woodpecker.ton;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * 验证序列化、反射、克隆对单列模式对破坏：当然，这些问题都可以利用单元素枚举类来解决
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public class Singleton implements Serializable, Cloneable {

    private static final Singleton ton;
    static {
        ton = new Singleton();
    }

    /**
     * 使用反射调利用私有构造器也是可以破坏单例的，要防止此情况发生，可以在私有的构造器中加一个判断，需要创建的对象不存在就创建,
     * 存在则说明是第二次调用，抛出 IllegalStateException 提示
     */
    private Singleton() {
        if (null != ton) {
            throw new IllegalStateException("cannot construct a singleton more than once.");
        }
    }
    public static Singleton getInstance() {
        return ton;
    }

    /**
     * 序列化破坏单列模式
     * readResolve() 是反序列化操作提供的一个很特别的钩子函数，它在从流中读取对象的 readObject(ObjectInputStream) 方法之后被调用，可以让开发人员控制对象的反序列化。
     * 我们在 readResolve() 方法中用原来的 instance 替换掉从流中读取到的新创建的 instance，就可以避免使用序列化方式破坏了单例
     * <pre>
     *     {@code
     *     private Object readResolve() {
     *     return ton;
     *     }}
     * </pre>
     * @throws Exception 异常
     */
    private static void checkSerial() throws Exception {
        Singleton ton1 = getInstance();
        Singleton ton2 = getInstance();
        System.out.println("is singleton pattern normally valid:" + (ton1 == ton2) + ".");

        File file = new File("singleton.txt");
        /*序列化对象*/
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(ton1);
        outputStream.close();

        /*反序列化对象*/
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        Singleton ton3 = (Singleton) inputStream.readObject();
        System.out.println("is singleton pattern valid for deSerial:" + (ton1 == ton3) + ".");
    }

    /**
     * 反射破坏单列模式
     *
     * @throws Exception 异常
     */
    private static void checkReflect() throws Exception {
        Singleton ton1 = getInstance();
        Singleton ton2 = getInstance();
        System.out.println("is singleton pattern normally valid:" + (ton1 == ton2) + ".");

        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        /*跳过权限检查，直接调用私有构造器构造对象：防止此情况发生，可以私有构造器中加一个判断即可*/
        Singleton ton3 = constructor.newInstance();
        System.out.println("is singleton pattern valid for reflect:" + (ton1 == ton3) + ".");
    }

    /**
     * 克隆破坏单列模式:clone()是Object的方法，每一个对象都是Object的子类，都有clone() 方法。clone()方法并不是调用构造函数来创建对象，而是直接拷贝内存区域
     * 阻止使用clone()方法创建单例实例的另一个实例？可以override它的clone()方法，使其抛出异常
     * <pre>
     *     {@code
     *     @Override
     *     public Object clone() {
     *     throw new CloneNotSupportedException();
     *     }
     *     }
     * </pre>
     * @throws Exception 异常
     */
    private static void checkClone() throws Exception {
        Singleton ton1 = getInstance();
        Singleton ton2 = getInstance();
        System.out.println("is singleton pattern normally valid:" + (ton1 == ton2) + ".");

        Singleton ton3 = (Singleton) ton1.clone();
        System.out.println("is singleton pattern valid for clone:" + (ton1 == ton3) + ".");
    }

    public static void main(String[] args) throws Exception {
        checkSerial();
        checkReflect();
        checkClone();
    }

}
