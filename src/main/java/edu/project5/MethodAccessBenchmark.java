package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS)
public class MethodAccessBenchmark {

    private static final String METHOD_NAME = "name";
    private Student student;
    private Method reflectMethod;
    private MethodHandle methodHandle;
    private Supplier<String> lambda;

    @Setup
    public void setup() throws Throwable {
        student = new Student("John", "Doe");

        // Reflection
        reflectMethod = Student.class.getDeclaredMethod(METHOD_NAME);
        reflectMethod.setAccessible(true);

        // Method Handle
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));

        // Lambda Metafactory
        MethodType getterType = MethodType.methodType(String.class);
        MethodHandle target = lookup.findVirtual(Student.class, METHOD_NAME, getterType);
        CallSite site = LambdaMetafactory.metafactory(
            lookup,
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            getterType.generic(),
            target,
            getterType
        );
        lambda = (Supplier<String>) site.getTarget().invoke(student);
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        bh.consume(student.name());
    }

    @Benchmark
    public void reflectionAccess(Blackhole bh) throws Throwable {
        bh.consume(reflectMethod.invoke(student));
    }

    @Benchmark
    public void methodHandleAccess(Blackhole bh) throws Throwable {
        bh.consume(methodHandle.invoke(student));
    }

    @Benchmark
    public void lambdaMetafactoryAccess(Blackhole bh) {
        bh.consume(lambda.get());
    }

    @SuppressWarnings("checkstyle:uncommentedmain")
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(MethodAccessBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .build();

        new Runner(options).run();
    }
}


