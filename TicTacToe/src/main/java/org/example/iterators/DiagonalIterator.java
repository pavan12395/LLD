package org.example.iterators;


import org.example.game.CellValue;

import java.util.Iterator;

public class DiagonalIterator implements Iterator<CellValue> {
    private int row;
    private String cells[][];

    private boolean isRev;

    public DiagonalIterator(String cells[][],boolean isRev){
        this.cells = cells;
        this.isRev = isRev;
        this.row = this.isRev ? 2 : 0;
    }

    @Override
    public boolean hasNext(){

        return this.isRev ? this.row>=0 : this.row<cells.length;
    }

    @Override
    public CellValue next(){
        CellValue ans = isRev ? new CellValue(row,2-row,this.cells[row][2-row]) : new CellValue(row,row,this.cells[row][row]);
        row = isRev ? row-1 : row+1;
        return ans;
    }
}