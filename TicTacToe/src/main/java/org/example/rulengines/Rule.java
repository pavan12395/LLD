package org.example.rulengines;

import org.example.game.Board;
import org.example.game.GameInfo;
import org.example.game.GameState;

import java.util.function.Function;

public class Rule<T extends Board,R>{
    private Function<T,R> function;

    public Rule(Function<T,R> function){
        this.function = function;
    }

    public R getResult(T board){
        return function.apply(board);
    }
}