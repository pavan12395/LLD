package org.example.cells;

public class TicTacToeCellValue {


    private int row;

    private int col;

    private String value;



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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public TicTacToeCellValue(int row,int col,String value){
        this.row = row;
        this.col = col;
        this.value = value;
    }

}