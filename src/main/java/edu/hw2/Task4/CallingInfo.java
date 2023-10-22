package edu.hw2.Task4;

public record CallingInfo(String className, String methodName) {
    private static final int CALLER_STACK_INDEX = 2;
    private static final String UNKNOWN = "Unknown";

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length < CALLER_STACK_INDEX + 1) {
            return new CallingInfo(UNKNOWN, UNKNOWN);
        }

        StackTraceElement caller = stackTrace[CALLER_STACK_INDEX];
        return new CallingInfo(caller.getClassName(), caller.getMethodName());
    }
}
