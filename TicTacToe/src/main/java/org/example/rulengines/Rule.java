package org.example.rulengines;

import org.example.boards.Board;

import java.util.function.Function;

public class Rule<CellBoard,R>{
    private Function<CellBoard,R> function;

    public Rule(Function<CellBoard,R> function){
        this.function = function;
    }

    public R getResult(CellBoard board){
        return function.apply(board);
    }
}