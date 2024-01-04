package org.example.api;


import javafx.css.Rule;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.GameState;
import org.example.game.Move;
import org.example.boards.TicTacToeBoard;
import org.example.rulengines.RuleEngine;
import org.example.rulengines.TicTacToeRuleEngine;

import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private Map<String,RuleEngine> ruleEngineMap;

    public GameEngine(){
        ruleEngineMap = new HashMap<>();
        ruleEngineMap.put(TicTacToeBoard.class.getName(),new TicTacToeRuleEngine());
    }
    public GameState move(Board board, Move move) throws Exception {
        RuleEngine ruleEngine = ruleEngineMap.get(board.getClass().getName());
        if(ruleEngine.checkMove(move,board)){
            board.setCell(move.getCell(),move.getEntity().getPlayerSymbol());
            return ruleEngine.getState(board);
        } else {
            throw new Exception("Invalid Move!");
        }
    }

    public Board start(Cell.BoardType boardType) throws Exception {
        if(boardType == Cell.BoardType.TICTACTOE){
            return new TicTacToeBoard();
        }
        else {
            throw new Exception("UnknownBoardException");
        }
    }
}