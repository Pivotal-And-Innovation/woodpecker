package com.woodpecker.jvm.dynamic;

import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.*;

/**
 * invokeDynamic指令基础演示：该实例中的方法名称不能随意动，更不能把几个方法合并到一起，因为它们被INDY工具读取的。
 * 详解见P286
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public class InvokeDynamicTest {

    public static void main(String[] args) throws Throwable {
        INDY_BootstrapMethod().invokeExact("my god.");
    }

    private static void testMethod(String string) {
        System.out.println("Hello String:" + string);
    }

    private static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType type) throws Exception {
        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, type));
    }

    private static MethodType MT_BootstrapMethod() {
        return MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)" +
                "Ljava/lang/invoke/CallSite;", null);
    }

    private static MethodHandle MH_BootstrapMethod() throws Exception {
        return lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable {
        CallSite cs = (CallSite)MH_BootstrapMethod().invokeWithArguments(lookup(), "testMethod", MethodType.fromMethodDescriptorString("(Ljava/lang/String;" +
                ")V", null));
        return cs.dynamicInvoker();
    }

}
