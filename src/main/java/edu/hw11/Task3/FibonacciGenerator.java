package edu.hw11.Task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("MagicNumber")
public class FibonacciGenerator implements ByteCodeAppender {
    @Override
    public @NotNull Size apply(
        @NotNull MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        @NotNull MethodDescription methodDescription
    ) {
        Label start = new Label();
        Label moreThanZero = new Label();
        Label notEqualToOne = new Label();
        Label forStart = new Label();
        Label forEnd = new Label();
        Label end = new Label();

        int inputNumber = 0;    // Индекс вводимого числа
        int previousNumber = 1; // Предыдущее число Фибоначчи
        int currentNumber = 3;  // Текущее число Фибоначчи
        int loopIndex = 5;      // Счетчик цикла

        methodVisitor.visitCode();
        methodVisitor.visitFrame(Opcodes.F_NEW, 1, new Object[] {Opcodes.INTEGER}, 0, null);

        // Определение локальных переменных в методе
        methodVisitor.visitLocalVariable("inputNumber", "I", null, start, end, inputNumber);
        methodVisitor.visitLocalVariable("previousNumber", "J", null, start, end, previousNumber);
        methodVisitor.visitLocalVariable("currentNumber", "J", null, start, end, currentNumber);
        methodVisitor.visitLocalVariable("loopIndex", "I", null, start, end, loopIndex);

        methodVisitor.visitLabel(start);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitJumpInsn(Opcodes.IFGT, moreThanZero);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitInsn(Opcodes.LRETURN);
        methodVisitor.visitLabel(moreThanZero);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

        // если inputNumber <= 0, то возвращаем 0
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, notEqualToOne);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitInsn(Opcodes.LRETURN);
        methodVisitor.visitLabel(notEqualToOne);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

        // Инициализация переменных для вычисления чисел Фибоначчи
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, previousNumber);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, currentNumber);

        // Настройка цикла для вычисления числа Фибоначчи
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, loopIndex);
        methodVisitor.visitLabel(forStart);
        methodVisitor.visitFrame(
            Opcodes.F_APPEND,
            3,
            new Object[] {Opcodes.LONG, Opcodes.LONG, Opcodes.INTEGER},
            0,
            null
        );
        methodVisitor.visitVarInsn(Opcodes.ILOAD, loopIndex);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, inputNumber);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGT, forEnd);

        // Вычисление числа Фибоначчи
        methodVisitor.visitVarInsn(Opcodes.LLOAD, currentNumber);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, currentNumber);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, previousNumber);
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, currentNumber);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, previousNumber);

        // Инкремент счетчика цикла
        methodVisitor.visitIincInsn(loopIndex, 1);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, forStart);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

        methodVisitor.visitLabel(forEnd);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

        // Возврат вычисленного числа Фибоначчи
        methodVisitor.visitLabel(end);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, currentNumber);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        // Установка максимального размера стека и числа локальных переменных
        methodVisitor.visitMaxs(6, 6);
        methodVisitor.visitEnd();

        return new Size(6, 6);
    }
}
