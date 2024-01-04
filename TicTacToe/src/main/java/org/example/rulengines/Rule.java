package org.example.rulengines;

import org.example.game.Board;

import java.util.function.Function;

public class Rule {
    private Function<Board,String> function;

    public Rule(Function<Board,String> function){
        this.function = function;
    }

    public String getResult(Board board){
        return function.apply(board);
    }
}