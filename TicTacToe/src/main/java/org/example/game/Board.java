package org.example.game;


import org.example.game.Cell;
import org.example.game.GameState;
import org.example.game.Move;
import org.example.players.Entity;

public interface Board {
    abstract void move(Move move) throws Exception;

    abstract boolean isOccupied(Cell cell) throws Exception;
    abstract GameState getState() throws Exception;

    abstract String[][] getCells();

    abstract Move randomMove(Entity entity) throws  Exception;

    abstract Move smartMove(Entity entity) throws  Exception;
}