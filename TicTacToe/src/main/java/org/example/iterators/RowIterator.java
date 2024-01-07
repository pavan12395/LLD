package org.example.iterators;


import org.example.cells.TicTacToeCellValue;

import java.util.Iterator;

public class RowIterator implements Iterator<TicTacToeCellValue> {
    private int row;
    private String cells[][];

    private int col;
    public RowIterator(String cells[][],int row){
        this.cells = cells;
        this.row = row;
        this.col = 0;
    }

    @Override
    public boolean hasNext(){
        return this.col<cells[0].length;
    }

    @Override
    public TicTacToeCellValue next(){
        return new TicTacToeCellValue(row,col,this.cells[row][col++]);
    }
}