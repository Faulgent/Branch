package com.company;

public enum Level {
    NOVICE(5);

    final int size;

    Level(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
