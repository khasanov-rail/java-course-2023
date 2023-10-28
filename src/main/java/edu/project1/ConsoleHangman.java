package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ConsoleHangman {
    private static final int MAX_ATTEMPTS = 5;
    private static final Logger LOGGER = LogManager.getLogger();

    private Dictionary dictionary;
    private Session session;

    ConsoleHangman() {
        this.dictionary = new SimpleDictionary();
        this.session = new Session(dictionary.randomWord(), MAX_ATTEMPTS);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        if (session.getAnswer().length() <= 1) {
            LOGGER.error("The chosen word has an incorrect length. Exiting the game.");
            return;
        }

        while (true) {
            LOGGER.info("Guess a letter:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) { // спец слово quit для быстрого окончания игры
                printState(session.giveUp());
                break;
            }

            if (input.length() != 1) {
                LOGGER.warn("Please enter only one letter.");
                continue;
            }

            GuessResult result = session.guess(input.charAt(0));
            printState(result);

            if (result instanceof GuessResult.Win || result instanceof GuessResult.Defeat) {
                break;
            }
        }
    }

    private void printState(GuessResult guess) {
        LOGGER.info(guess.message());
        LOGGER.info("The word: " + new String(guess.state()));
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
