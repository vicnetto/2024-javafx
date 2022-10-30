package com.vicnetto.javafx2048.model;

import com.vicnetto.javafx2048.watcher.Watcher;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Arrays;

public class GameInformation implements Watcher {

    public static final Integer DEFAULT_GOAL = 2048;
    public static final Integer[] POSSIBLE_GOALS = {256, 512, 1024, 2048};

    private final IntegerProperty score;

    private final IntegerProperty wins;

    private final IntegerProperty attempts;

    private final IntegerProperty bestScore;

    private final IntegerProperty goal;

    public GameInformation() {
        this.wins = new SimpleIntegerProperty(0);
        this.attempts = new SimpleIntegerProperty(0);
        this.score = new SimpleIntegerProperty(0);
        this.bestScore = new SimpleIntegerProperty(0);
        this.goal = new SimpleIntegerProperty(2048);
    }

    public void setNewBoardSize() {
        int newGoal = Arrays.binarySearch(POSSIBLE_GOALS, goal.get()) + 1;

        goal.set(newGoal < POSSIBLE_GOALS.length ? POSSIBLE_GOALS[newGoal] : POSSIBLE_GOALS[0]);
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

    public int getGoal() {
        return goal.get();
    }

    public IntegerProperty goalProperty() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal.set(goal);
    }

    @Override
    public void newGame() {
        score.set(0);
        attempts.set(attempts.get() + 1);
        bestScore.set(Math.max(score.get(), bestScore.get()));
    }
}