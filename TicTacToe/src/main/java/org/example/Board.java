package org.example;


public interface Board {
    abstract void move(Move move) throws Exception;
    abstract GameState getState();
}