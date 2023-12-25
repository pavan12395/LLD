package org.example.cells;


import org.example.Cell;
import org.example.Player;

public class TicTacToeCell implements Cell {

    public int row;
    public int col;


    public TicTacToeCell(int row,int col){
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

    public boolean validate(){
        if(this.row<0 || this.col<0 || this.row>=3 || this.col>=3){
            return false;
        }
        return true;
    }

}