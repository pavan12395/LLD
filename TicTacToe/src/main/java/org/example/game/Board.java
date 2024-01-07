package org.example.game;


import org.example.cells.Cell;

import java.util.List;
import java.util.Map;

public interface Board {
    public abstract void setCell(Cell cell,String value) throws Exception;
    public abstract String getCell(Cell cell) throws Exception;

    public abstract boolean isOccupied(Cell cell) throws Exception;
    public abstract Board copy();

    public abstract Map<String,Integer> getPlayerMoves();
}