package org.example.moves;


import org.example.cells.TicTacToeCell;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.players.Entity;

public class TicTacToeMove extends Move {


    public TicTacToeMove(TicTacToeCell cell, Entity entity) {
        super(cell,entity);
    }

    @Override
    public TicTacToeCell getCell() {
        return (TicTacToeCell) cell;
    }


    @Override
    public void setCell(Cell cell) throws Exception {
        if(cell instanceof TicTacToeCell)
        {
            this.cell = cell;
        }
        else {
            throw new Exception("Invalid Cell to set for TicTacToeMove");
        }
    }


}