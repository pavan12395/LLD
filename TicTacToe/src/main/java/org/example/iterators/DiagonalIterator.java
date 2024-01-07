package org.example.iterators;


import org.example.cells.TicTacToeCellValue;

import java.util.Iterator;

public class DiagonalIterator implements Iterator<TicTacToeCellValue> {
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
    public TicTacToeCellValue next(){
        TicTacToeCellValue ans = isRev ? new TicTacToeCellValue(row,2-row,this.cells[row][2-row]) : new TicTacToeCellValue(row,row,this.cells[row][row]);
        row = isRev ? row-1 : row+1;
        return ans;
    }
}