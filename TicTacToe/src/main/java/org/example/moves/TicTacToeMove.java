package org.example.moves;


import org.example.cells.Cell;
import org.example.game.Move;
import org.example.players.Entity;

public class TicTacToeMove extends Move {

    private Cell cell;

    public TicTacToeMove(Cell cell, Entity entity) {
        super(entity);
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }
    public void setCell(Cell cell)  {
        this.cell = cell;
    }


}