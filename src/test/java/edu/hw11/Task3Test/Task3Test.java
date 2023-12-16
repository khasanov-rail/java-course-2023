package edu.hw11.Task3Test;

import edu.hw11.Task3.FibonacciGenerator;
import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    static Class<?> dynamicFibonacciClass;

    @BeforeAll
    static void setUp() {
        dynamicFibonacciClass = new ByteBuddy()
            .subclass(Object.class)
            .name("DynamicFibonacci")
            .defineMethod("computeFibonacci", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class, "number")
            .intercept(new Implementation.Simple(new FibonacciGenerator()))
            .make()
            .load(Task3Test.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
    }

    @Test
    @DisplayName("Вычисление 15-го числа Фибоначчи")
    void testFibonacciFifteen() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Arrange
        int number = 15;
        long expected = 610;

        // Act
        long result = (long) dynamicFibonacciClass.getMethod("computeFibonacci", int.class).invoke(null, number);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Вычисление 3-го числа Фибоначчи")
    void testFibonacciThree() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Arrange
        int number = 3;
        long expected = 2;

        // Act
        long result = (long) dynamicFibonacciClass.getMethod("computeFibonacci", int.class).invoke(null, number);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Вычисление Фибоначчи для отрицательного числа")
    void testFibonacciNegativeNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Arrange
        int number = -5;
        long expected = 0;

        // Act
        long result = (long) dynamicFibonacciClass.getMethod("computeFibonacci", int.class).invoke(null, number);

        // Assert
        assertThat(result).isEqualTo(expected);
    }
}
