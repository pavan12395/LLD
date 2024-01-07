package org.example.game;



// all are in millis.
public class GameConfig{

    private boolean timed;
    private boolean checkForMove;
    private int maxTimePerPlayer;

    private int maxTimePerMove;


    public boolean isCheckForMove() {
        return checkForMove;
    }

    public void setCheckForMove(boolean checkForMove) {
        this.checkForMove = checkForMove;
    }

    public boolean isTimed() {
        return timed;
    }

    public void setTimed(boolean timed) {
        this.timed = timed;
    }


    public int getMaxTimePerPlayer() {
        return maxTimePerPlayer;
    }

    public void setMaxTimePerPlayer(int maxTimePerPlayer) {
        this.maxTimePerPlayer = maxTimePerPlayer;
    }


    public int getMaxTimePerMove() {
        return maxTimePerMove;
    }

    public void setMaxTimePerMove(int maxTimePerMove) {
        this.maxTimePerMove = maxTimePerMove;
    }

    public GameConfig(boolean isTimed,boolean checkForMove,int maxTimePerMove,int maxTimePerPlayer){
        this.timed = isTimed;
        this.checkForMove = checkForMove;
        this.maxTimePerMove = maxTimePerMove;
        this.maxTimePerPlayer = maxTimePerPlayer;
    }
}