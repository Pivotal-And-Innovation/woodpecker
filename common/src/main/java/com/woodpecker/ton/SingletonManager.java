package com.woodpecker.ton;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用Map容器来管理单例模式
 * 在程序的初始，我们将一组单例类型注入到一个统一的管理类中来维护，即将这些实例存放在一个 Map 登记薄中，在使用时则根据 key 来获取对象对应类型的单例对象，
 * 对于已经登记过的实例，从 Map 直接返回实例；对于没有登记的，则先登记再返回。从而在对用户隐藏具体实现、降低代码耦合度的同时，也降低了用户的使用成本。
 *
 * @author Glenn
 * @since 2017-03-28
 */
@SuppressWarnings("all")
public class SingletonManager {

    private static final Map<String, Object> tonMap = new HashMap<>();

    private SingletonManager() {
        throw new AssertionError();
    }

    public static void registerTon(String key, Object instance) {
        if (!tonMap.containsKey(key)) {
            tonMap.put(key, instance);
        }
    }

    public static Object getTon(String key) {
        return tonMap.get(key);
    }

}
