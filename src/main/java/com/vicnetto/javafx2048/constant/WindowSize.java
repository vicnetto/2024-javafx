package com.vicnetto.javafx2048.constant;

public enum WindowSize {
    WIDTH(500),
    LENGTH(650);

    private final int value;

    WindowSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
