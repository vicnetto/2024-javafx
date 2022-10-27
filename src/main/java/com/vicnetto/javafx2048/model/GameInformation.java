package com.vicnetto.javafx2048.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameInformation {

    private final IntegerProperty score;

    private final IntegerProperty wins;

    private final IntegerProperty attempts;

    private final IntegerProperty bestScore;

    public GameInformation() {
        this.wins = new SimpleIntegerProperty(0);
        this.attempts = new SimpleIntegerProperty(0);
        this.score = new SimpleIntegerProperty(0);
        this.bestScore = new SimpleIntegerProperty(0);
    }

    public int getWins() {
        return wins.get();
    }

    public IntegerProperty winsProperty() {
        return wins;
    }

    public void addOneWin() {
        this.wins.set(wins.get() + 1);
    }

    public int getAttempts() {
        return attempts.get();
    }

    public IntegerProperty attemptsProperty() {
        return attempts;
    }

    public void addOneAttempt() {
        this.attempts.set(this.attempts.get() + 1);
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public int getBestScore() {
        return bestScore.get();
    }

    public IntegerProperty bestScoreProperty() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore.set(bestScore);
    }
}