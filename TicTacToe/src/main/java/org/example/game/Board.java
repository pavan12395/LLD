package org.example.game;


import org.example.cells.Cell;
import org.example.players.Entity;

public abstract class Board {

    private String previousPlayer;

    public Board(){
        this.previousPlayer = null;
    }

    protected void setPreviousPlayer(String symbol) {
        this.previousPlayer = symbol;
    }

    protected String getPreviousEntity() {
        return this.previousPlayer;
    }
    public abstract void setCell(Cell cell,String value) throws Exception;

    public abstract String getCell(Cell cell);

    public abstract boolean isOccupied(Cell cell);

    public abstract String[][] getCells();
}