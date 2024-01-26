package org.example.rulengines;

import org.example.boards.Board;

import java.util.function.Function;

public class Rule<Board,R>{
    private Function<Board,R> function;

    public Rule(Function<Board,R> function){
        this.function = function;
    }

    public R getResult(Board board){
        return function.apply(board);
    }
}

