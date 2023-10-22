package edu.project1;

import org.jetbrains.annotations.NotNull;

class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = new char[answer.length()];
        for (int i = 0; i < answer.length(); i++) {
            this.userAnswer[i] = '*';
        }
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    @NotNull
    GuessResult guess(char guess) {
        boolean isHit = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                userAnswer[i] = guess;
                isHit = true;
            }
        }

        if (isHit) {
            if (new String(userAnswer).equals(answer)) {
                return new GuessResult.Win(userAnswer, attempts, maxAttempts, "You won!");
            } else {
                return new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts, "Hit!");
            }
        } else {
            attempts++;
            if (attempts >= maxAttempts) {
                return new GuessResult.Defeat(userAnswer, attempts, maxAttempts, "You lost!");
            } else {
                return new GuessResult.FailedGuess(
                    userAnswer,
                    attempts,
                    maxAttempts,
                    "Missed, mistake " + attempts + " out of " + maxAttempts + "."
                );
            }
        }
    }

    @NotNull
    GuessResult giveUp() {
        return new GuessResult.Defeat(userAnswer, attempts, maxAttempts, "You gave up!");
    }

    String getAnswer() {
        return answer;
    }
}
