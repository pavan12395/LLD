package org.example.api;


import org.example.game.*;
import org.example.boards.TicTacToeBoard;
import org.example.moves.TicTacToeMove;
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
        RuleEngine ruleEngine = ruleEngineMap.get(TicTacToeBoard.class.getName());
        GameState gameState = ruleEngine.getState(board);
        if(gameState.getGameResult().equals(GameResult.OVER)){
            throw new Exception("Game Over!");
        }
        if(board instanceof TicTacToeBoard) {
            if(move instanceof TicTacToeMove) {
                TicTacToeMove move1 = (TicTacToeMove)move;
                board.setCell(move1.getCell(), move1.getEntity().getPlayerSymbol());
                return ruleEngine.getState(board);
            }
            else {
                throw new Exception("Invalid Move!");
            }
        }
        else {
            throw new Exception("Invalid Board");
        }
    }

    public Board start(BoardType boardType) throws Exception {
        if(boardType == BoardType.TICTACTOE){
            return new TicTacToeBoard();
        }
        else {
            throw new Exception("UnknownBoardException");
        }
    }
}