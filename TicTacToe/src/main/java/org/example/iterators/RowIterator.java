package org.example.iterators;


import org.example.game.CellValue;

import java.util.Iterator;

public class RowIterator implements Iterator<CellValue> {
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
    public CellValue next(){
        return new CellValue(row,col,this.cells[row][col++]);
    }
}