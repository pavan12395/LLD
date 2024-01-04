package org.example.game;


import org.example.game.Cell;
import org.example.game.GameState;
import org.example.game.Move;
import org.example.players.Entity;

public interface Board {
    abstract void setCell(Cell cell,String value) throws Exception;

    abstract String getCell(Cell cell) throws Exception;
}