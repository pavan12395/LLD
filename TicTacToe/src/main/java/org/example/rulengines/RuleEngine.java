package org.example.rulengines;

import org.example.game.Board;
import org.example.game.GameState;

import java.util.List;

public abstract class RuleEngine {

    protected List<Rule> rules;
    public abstract GameState getState(Board board) throws Exception;

}