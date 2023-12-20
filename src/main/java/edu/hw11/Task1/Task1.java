package edu.hw11.Task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class Task1 {
    public Class<?> createHelloByteBuddyClass() throws Exception {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("edu.hw11.task1.HelloByteBuddy")
            .method(ElementMatchers.named("toString")) // Указываем, что хотим переопределить метод toString
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            // Переопределение метода toString, чтобы он возвращал заданную строку
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
    }
}
