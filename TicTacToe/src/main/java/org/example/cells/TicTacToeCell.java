package org.example.cells;


import org.example.game.Cell;

public class TicTacToeCell implements Cell {

    private int row;
    private int col;

    public TicTacToeCell(int row,int col) {
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }


    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }



}