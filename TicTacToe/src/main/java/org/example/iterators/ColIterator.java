package org.example.iterators;


import org.example.game.Cell;
import org.example.game.CellValue;

import java.util.Iterator;

public class ColIterator implements Iterator<CellValue> {
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
    public CellValue next(){
        return new CellValue(row,col,this.cells[row++][col]);
    }
}