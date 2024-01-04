package org.example.boards;


import org.example.cells.Cell;
import org.example.game.Board;

public class TicTacToeBoard implements Board {

    private String cells[][];



    public TicTacToeBoard(){
        cells = new String[3][3];
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells.length;j++){
                cells[i][j]="-";
            }
        }
    }

    public void setCell(Cell cell, String value){
            int row = cell.getRow();
            int col = cell.getCol();
            this.cells[row][col] = value;
    }

    public String getCell(Cell cell){
            int row = cell.getRow();
            int col = cell.getCol();
            return this.cells[row][col];
    }

    public String[][] getCells(){
        return this.cells;
    }

    public boolean isOccupied(Cell cell) {
            return !this.cells[cell.getRow()][cell.getCol()].equals("-");
    }



}