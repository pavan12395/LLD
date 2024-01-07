package org.example.iterators;


import org.example.cells.TicTacToeCellValue;

import java.util.Iterator;

public class ColIterator implements Iterator<TicTacToeCellValue> {
    private int row;
    private String cells[][];

    private int col;
    public ColIterator(String cells[][],int col){
        this.cells = cells;
        this.col = col;
        this.row = 0;
    }

    @Override
    public boolean hasNext(){
        return this.row<cells.length;
    }

    @Override
    public TicTacToeCellValue next(){
        return new TicTacToeCellValue(row,col,this.cells[row++][col]);
    }
}