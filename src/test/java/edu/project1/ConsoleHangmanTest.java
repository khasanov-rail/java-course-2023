package edu.project1;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.ErrorHandler;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleHangmanTest {

    private ConsoleHangman game;

    @Plugin(name = "TestAppender", category = "Core", elementType = "appender", printObject = true)
    public static class TestAppender implements Appender {
        private static final List<String> log = new LinkedList<>();
        private static final PatternLayout layout = PatternLayout.newBuilder().withPattern("%m%n").build();

        @PluginFactory public static TestAppender createAppender() {
            return new TestAppender();
        }

        @Override public void append(LogEvent event) {
            log.add(layout.toSerializable(event));
        }

        public static String getLog() {
            return String.join("", log);
        }

        public static void clearLog() {
            log.clear();
        }

        @Override public String getName() {
            return "TestAppender";
        }

        @Override public Layout<? extends Serializable> getLayout() {
            return layout;
        }

        @Override public boolean ignoreExceptions() {
            return false;
        }

        @Override public ErrorHandler getHandler() {
            return null;
        }

        @Override public void setHandler(ErrorHandler handler) {

        }

        @Override public State getState() {
            return State.STARTED;
        }

        @Override public void initialize() {

        }

        @Override public void start() {

        }

        @Override public void stop() {

        }

        @Override public boolean isStarted() {
            return true;
        }

        @Override public boolean isStopped() {
            return false;
        }
    }

    @BeforeEach void setUp() {
        game = new ConsoleHangman();
        TestAppender.clearLog();
    }

    @AfterEach void restoreStreams() {
        System.setIn(System.in);
    }

    @Test
    void gameShouldNotStartWithIncorrectWordLength() {  // Игра не должна начинаться, если длина загаданного слова некорректна (в данном случае пустая строка)
        // Arrange
        game.setSession(new Session("", 5));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("The chosen word has an incorrect length. Exiting the game."));
    }

    @Test
    void gameShouldEndWithDefeatAfterMaxAttempts() { // Игра должна завершиться проигрышем после максимального количества попыток (в данном случае 1 попытка)
        // Arrange
        game.setSession(new Session("test", 1));
        System.setIn(new ByteArrayInputStream("a\nquit\n".getBytes()));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("You lost!"));
    }

    @Test
    void gameStateShouldChangeCorrectly() {  // Проверить, что состояние игры корректно изменяется при угадывании букв
        // Arrange
        game.setSession(new Session("test", 5));
        System.setIn(new ByteArrayInputStream("t\ne\ns\nquit\n".getBytes()));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("The word: test"));
    }

    @Test
    void gameShouldNotChangeStateOnTypo() {  // Игра не должна изменять свое состояние при вводе строки длиной больше 1 символа (опечатка)
        // Arrange
        game.setSession(new Session("test", 5));
        System.setIn(new ByteArrayInputStream("te\ns\nquit\n".getBytes()));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("Please enter only one letter."));
        assertFalse(TestAppender.getLog().contains("The word: t***"));
    }

    @Test
    void gameShouldAllowPlayerToGiveUp() {  // проверка возможности сдаться через написание специального слова "quit"
        // Arrange
        game.setSession(new Session("test", 5));
        System.setIn(new ByteArrayInputStream("quit\n".getBytes()));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("You gave up!"));
    }

    @Test void gameShouldDisplayWinMessageOnVictory() { // проверка сообщения о победе
        // Arrange
        game.setSession(new Session("test", 5));
        System.setIn(new ByteArrayInputStream("t\ne\ns\n".getBytes()));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("You won!"));
    }

    @Test void gameShouldDisplayDefeatMessageOnLoss() { // проверка сообщения о поражении
        // Arrange
        game.setSession(new Session("test", 1));
        System.setIn(new ByteArrayInputStream("a\n".getBytes()));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("You lost!"));
    }

    @Test void gameShouldEndWithWinWhenAllLettersAreGuessed() {  // проверка при угадывании всех букв
        // Arrange
        game.setSession(new Session("test", 5));
        System.setIn(new ByteArrayInputStream("t\ne\ns\n".getBytes()));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("You won!"));
    }

    @Test void gameShouldDisplayCurrentState() { // проверка отображения состояния игры
        // Arrange
        game.setSession(new Session("test", 5));
        System.setIn(new ByteArrayInputStream("t\nquit\n".getBytes()));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("The word: t**t"));
    }

    @Test void gameShouldDisplayCorrectWordOnLoss() { // проверка вывода при проигрыше
        // Arrange
        game.setSession(new Session("test", 1));
        System.setIn(new ByteArrayInputStream("a\nquit\n".getBytes()));

        // Act
        game.run();

        // Assert
        assertTrue(TestAppender.getLog().contains("The word: ****"));
    }
}
