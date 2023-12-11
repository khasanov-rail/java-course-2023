package edu.hw10.Task2;

import edu.hw10.Task2.annotations.Cache;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class CacheInvocationHandler implements InvocationHandler {
    private final Object delegate;
    private final Map<String, Object> cache = new HashMap<>();

    public CacheInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            String key = method.getName() + args[0];
            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            Object result = method.invoke(delegate, args);
            cache.put(key, result);

            if (method.getAnnotation(Cache.class).persist()) {
                saveResultToDisk(key, result);
            }

            return result;
        }

        return method.invoke(delegate, args);
    }

    private void saveResultToDisk(String key, Object result) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(key + ".cache"))) {
            out.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
