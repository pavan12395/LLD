package org.example.game;

import org.example.players.Entity;
import org.example.rulengines.RuleEngine;


public class Game {
    private String previousPlayerSymbol;

    private Board board;

    private RuleEngine ruleEngine;

    private GameConfig gameConfig;

    private GameState gameState;


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Game(Board board, GameConfig gameConfig) {
        this.previousPlayerSymbol = null;
        this.board = board;
        this.ruleEngine = new RuleEngine();
        this.gameConfig = gameConfig;
    }

    private void recordTimeMetrics(Entity entity, long lastPlayedTimeStamp, long totalTimeSpent) {
        entity.setTotalTimeSpent(totalTimeSpent);
        entity.setLastPlayedTimeStamp(lastPlayedTimeStamp);
    }

    private boolean checkTimedOutPerMove(Entity entity,long currentTimeStamp,long timeSpentForCurrentMove,long totalTimeSpent) {
        return timeSpentForCurrentMove > this.gameConfig.getMaxTimePerMove();
    }

    private boolean checkTimedOut(Move move){
        long currentTimeStamp = move.getTimeStamp();
        Entity entity = move.getEntity();
        if(this.gameConfig.isTimed()){
            long timeSpentForCurrentMove = (currentTimeStamp - entity.getLastPlayedTimeStamp());
            long totalTimeSpent = entity.getTotalTimeSpent() + timeSpentForCurrentMove;
            recordTimeMetrics(move.getEntity(),currentTimeStamp,totalTimeSpent);
            if(this.gameConfig.isCheckForMove()){
                return checkTimedOutPerMove(entity,currentTimeStamp,timeSpentForCurrentMove,totalTimeSpent);
            }
            else if(totalTimeSpent > this.gameConfig.getMaxTimePerPlayer()){
                return true;
            }
        }
        return false;
    }

    public void move(Move move) throws  Exception {
        if(this.gameState!=null && this.gameState.getGameResult()==GameResult.TIMED_OUT){
            throw new Exception("Invalid TimedOut Exception");
        }
        else if(this.checkTimedOut(move)){
            this.gameState = new GameState(GameResult.TIMED_OUT,move.getEntity().flip().getPlayerSymbol());
            throw new Exception("TimedOut move!");
        }
        GameState gameState = this.getState();
        if(gameState.getGameResult().equals(GameResult.OVER)){
            throw new Exception("Invalid Move!");
        }
        
        else if(!this.validateMove(move)){
            throw new Exception("Invalid Move!");
        }
        board.setCell(move.getCell(), move.getEntity().getPlayerSymbol());
        this.previousPlayerSymbol = move.getEntity().getPlayerSymbol();
    }

    public GameInfo getInfo() throws Exception {
        if(this.gameState!=null && this.gameState.getGameResult()==GameResult.TIMED_OUT){
            return new GameInfo("-",false,board.getPlayerMoves(),this.gameState);
        }
        else {
            return ruleEngine.getInfo(board);
        }
    }

    public GameState getState(){
        if(this.gameState!=null && this.gameState.getGameResult()==GameResult.TIMED_OUT){
            return gameState;
        }
        else {
            return ruleEngine.getState(board);
        }
    }

    private boolean validateMove(Move move) throws Exception{
        String currentPlayerSymbol = move.getEntity().getPlayerSymbol();
        return !currentPlayerSymbol.equals(previousPlayerSymbol) && move.getCell().validate() && !this.board.isOccupied(move.getCell());
    }


}