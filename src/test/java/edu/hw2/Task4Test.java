package edu.hw2.Task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4Test {

    @Test
    void testCallingInfoInsideMethod() {
        // Arrange
        String expectedClassName = "edu.hw2.Task4.Task4Test";
        String expectedMethodName = "testCallingInfoInsideMethod";

        // Act
        CallingInfo callingInfo = CallingInfo.callingInfo();

        // Assert
        assertEquals(expectedClassName, callingInfo.className(), "Class name should match");
        assertEquals(expectedMethodName, callingInfo.methodName(), "Method name should match");
    }

    @Test
    void testCallingInfoInsideLambda() {
        // Arrange
        String expectedClassName = "edu.hw2.Task4.Task4Test";
        String expectedMethodNamePrefix = "lambda$testCallingInfoInsideLambda$";

        // Act
        Runnable testRunnable = () -> {
            CallingInfo callingInfo = CallingInfo.callingInfo();

            // Assert
            assertEquals(expectedClassName, callingInfo.className(), "Class name should match");
            assertTrue(
                callingInfo.methodName().startsWith(expectedMethodNamePrefix),
                "Method name should start with the expected prefix"
            );
        };
        testRunnable.run();
    }

    @Test
    void testCallingInfoMethod() {
        // Arrange
        String expectedMethodName = "testCallingInfoMethod";

        // Act
        CallingInfo result = CallingInfo.callingInfo();

        // Assert
        assertEquals(expectedMethodName, result.methodName());
    }

    @Test
    void callingInfoShouldNameWhoHasCalledFunction() {
        // Arrange
        String expectedClassName = Task4Test.class.getName();
        String expectedMethodName = "callingInfoShouldNameWhoHasCalledFunction";

        // Act
        CallingInfo result = CallingInfo.callingInfo();

        // Assert
        assertEquals(expectedClassName, result.className(), "Class name should match");
        assertEquals(expectedMethodName, result.methodName(), "Method name should match");
    }

}
