package org.example.rulengines;

import org.example.boards.TicTacToeBoard;
import org.example.game.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleEngine {

    private Map<String,List<Rule>> stateRules;


    private Map<String,List<Rule>> forkRules;

    public RuleEngine(){
        stateRules = new HashMap<>();
        forkRules = new HashMap<>();
        List<Rule> ticTacToeBoardStateRules = TicTacToeBoard.getStateRules();
        stateRules.put(TicTacToeBoard.class.getName(),ticTacToeBoardStateRules);
        List<Rule> ticTacToeBoardForkRules = TicTacToeBoard.getForkRules(this);
        forkRules.put(TicTacToeBoard.class.getName(),ticTacToeBoardForkRules);
    }
    public GameState getState(Board board) {
        for(Rule<Board,GameState> rule : stateRules.get(board.getClass().getName())) {
            GameState gameState  = rule.getResult(board);
            if (gameState.getGameResult() != GameResult.PENDING) {
                return gameState;
            }
        }
        return new GameState(GameResult.PENDING,"-");
    }




    public GameInfo getInfo(Board board) throws Exception {
        for(Rule<Board,GameInfo> rule : forkRules.get(board.getClass().getName())) {
            GameInfo gameInfo = rule.getResult(board);
            if(gameInfo.isFork()){
                return gameInfo;
            }
        }
        return new GameInfo("-",false,board.getPlayerMoves(),this.getState(board));
    }



}