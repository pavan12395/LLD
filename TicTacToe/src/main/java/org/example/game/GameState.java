package org.example.game;


public class GameState {

    private GameResult gameResult;

    private String winner;

    public GameState(GameResult gameResult,String winner){
        this.gameResult = gameResult;
        this.winner = winner;
    }

    public void setGameResult(GameResult gameResult){this.gameResult = gameResult;}

    public GameResult getGameResult(){return this.gameResult;}

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String toString(){
        return this.getGameResult()==GameResult.OVER ? "Won by "+winner : "Need more moves";
    }
}