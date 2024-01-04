package org.example.game;


import org.example.cells.Cell;

public interface Board {
    abstract void setCell(Cell cell,String value);

    abstract String getCell(Cell cell);

    abstract boolean isOccupied(Cell cell);

    abstract String[][] getCells();
}