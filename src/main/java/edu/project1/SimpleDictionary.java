package edu.project1;

import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class SimpleDictionary implements Dictionary {
    private static final List<String> WORDS = List.of("hello", "world", "java", "main", "zero");
    // любое одно слово из списка, который нужно угадать
    private static final Random RANDOM = new Random();

    @Override
    public @NotNull String randomWord() {
        return WORDS.get(RANDOM.nextInt(WORDS.size()));
    }
}
