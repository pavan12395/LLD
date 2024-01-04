package org.example.rulengines;

import org.example.cells.Cell;
import org.example.game.Board;
import org.example.game.Move;
import org.example.game.GameState;
import org.example.players.Entity;

public abstract class RuleEngine {
    public abstract GameState getState(Board board) throws Exception;

}