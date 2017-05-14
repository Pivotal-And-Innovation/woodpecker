package com.woodpecker.jvm.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 动态生成的代理类：可观察其原理，本质还是通过反射机制
 *
 * @author Relax
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
public final class $Proxy0 extends Proxy implements ProxyInterface {
    private static Method m4;
    private static Method m1;
    private static Method m5;
    private static Method m0;
    private static Method m3;
    private static Method m2;

    public $Proxy0(InvocationHandler definedHandler) {
        super(definedHandler);
    }

    public final void grandFather() {
        try {
            h.invoke(this, m4, null);
            return;
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    public final boolean equals(Object paramObject) {
        try {
            return ((Boolean) h.invoke(this, m1, new Object[]{paramObject})).booleanValue();
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    public final void grandMother() {
        try {
            h.invoke(this, m5, null);
            return;
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    public final int hashCode() {
        try {
            return ((Integer) h.invoke(this, m0, null)).intValue();
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    public final void father() {
        try {
            h.invoke(this, m3, null);
            return;
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    public final String toString() {
        try {
            return (String) h.invoke(this, m2, null);
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    static {
        try {
            m4 = Class.forName("package com.woodpecker.jvm.proxy.dynamic.jdk.ProxyInterface").getMethod("grandFather", new Class[0]);
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[]{Class.forName("java.lang.Object")});
            m5 = Class.forName("package com.woodpecker.jvm.proxy.dynamic.jdk.ProxyInterface").getMethod("grandMother", new Class[0]);
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            m3 = Class.forName("package com.woodpecker.jvm.proxy.dynamic.jdk.ProxyInterface").getMethod("father", new Class[0]);
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
        } catch (NoSuchMethodException localNoSuchMethodException) {
            throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
        }
    }

}
