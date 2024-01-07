package org.example.boards;


import org.example.cells.Cell;

import java.util.Map;

public interface Board {

    public abstract void setCell(Cell cell, String value) throws Exception;
    public abstract String getCell(Cell cell) throws Exception;

    public abstract Map<String,Integer> getPlayerMoves();

    public abstract boolean isOccupied(Cell cell) throws Exception;
    public abstract Board copy();
}