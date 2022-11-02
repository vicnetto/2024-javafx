package com.vicnetto.javafx2048.model;

import com.vicnetto.javafx2048.view.GameInformationView;
import com.vicnetto.javafx2048.watcher.Watcher;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameInformation implements Watcher {

    private final IntegerProperty score;

    private final IntegerProperty wins;

    private final IntegerProperty attempts;

    private final IntegerProperty bestScore;

    private final IntegerProperty goal;

    private boolean gameWon = false;

    public GameInformation() {
        this.wins = new SimpleIntegerProperty(0);
        this.attempts = new SimpleIntegerProperty(0);
        this.score = new SimpleIntegerProperty(0);
        this.bestScore = new SimpleIntegerProperty(0);
        this.goal = new SimpleIntegerProperty(2048);
    }

    public void addWin(GameInformationView gameInformationView) {
        if (score.get() >= goal.get() && !gameWon) {
            setGameWon(true);
            gameInformationView.setGameWinnerMessage();

            wins.set(wins.get() + 1);
        }
    }

    public IntegerProperty winsProperty() {
        return wins;
    }

    public IntegerProperty attemptsProperty() {
        return attempts;
    }

    public void addOneAttempt() {
        this.attempts.set(this.attempts.get() + 1);
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

    public void setGoal(int goal) {
        this.goal.set(goal);
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    @Override
    public void newGame() {
        score.set(0);
        addOneAttempt();
        bestScore.set(Math.max(score.get(), bestScore.get()));
    }
}