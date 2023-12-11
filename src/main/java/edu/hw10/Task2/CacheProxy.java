package edu.hw10.Task2;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheProxy() {
    }

    public static <T> T create(T delegate, Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
            clazz.getClassLoader(),
            new Class<?>[] {clazz},
            new CacheInvocationHandler(delegate)
        );
    }
}
