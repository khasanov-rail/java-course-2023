package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Random;

public class RandomObjectGenerator {

    private static final int DEFAULT_STRING_LENGTH = 10;
    private static final int ASCII_LOWER_A = 97;
    private static final int ASCII_LOWER_Z = 123;

    private final Random random = new Random();

    public <T> T nextObject(Class<T> clazz, String factoryMethodName) throws Exception {
        Method factoryMethod = null;
        if (factoryMethodName != null) {
            factoryMethod = clazz.getDeclaredMethod(factoryMethodName);
        }
        return createObject(clazz, factoryMethod);
    }

    public <T> T nextObject(Class<T> clazz) throws Exception {
        return createObject(clazz, null);
    }

    private <T> T createObject(Class<T> clazz, Method factoryMethod) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        Object[] params = generateParametersForConstructor(constructor);

        T object = null;
        if (factoryMethod != null) {
            object = (T) factoryMethod.invoke(null, params);
        } else {
            object = (T) constructor.newInstance(params);
        }

        return object;
    }

    private Object[] generateParametersForConstructor(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        Object[] values = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            values[i] = generateRandomValue(parameters[i]);
        }

        return values;
    }

    private Object generateRandomValue(Parameter parameter) {
        Class<?> type = parameter.getType();
        Object value = null;

        if (type.equals(int.class) || type.equals(Integer.class)) {
            int min = parameter.isAnnotationPresent(Min.class)
                ? parameter.getAnnotation(Min.class).value()
                : Integer.MIN_VALUE;
            int max = parameter.isAnnotationPresent(Max.class)
                ? parameter.getAnnotation(Max.class).value()
                : Integer.MAX_VALUE;
            value = random.nextInt(max - min + 1) + min;
        } else if (type.equals(String.class)) {
            value = generateRandomString();
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            double min = parameter.isAnnotationPresent(Min.class)
                ? parameter.getAnnotation(Min.class).value()
                : Double.MIN_VALUE;
            double max = parameter.isAnnotationPresent(Max.class)
                ? parameter.getAnnotation(Max.class).value()
                : Double.MAX_VALUE;
            value = min + (max - min) * random.nextDouble();
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            value = random.nextBoolean();
        }

        return value;
    }

    private String generateRandomString() {
        return random.ints(ASCII_LOWER_A, ASCII_LOWER_Z)
            .limit(DEFAULT_STRING_LENGTH)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}
