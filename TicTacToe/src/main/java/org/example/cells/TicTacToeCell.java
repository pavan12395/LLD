package org.example.cells;



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

    @Override
    public boolean validate(){
        return this.row>=0 && this.col>=0 && this.row<3 && this.col<3;
    }
    
}