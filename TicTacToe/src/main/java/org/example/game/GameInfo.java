package org.example.game;


import org.example.cells.Cell;

import java.util.Map;

public class GameInfo {



    private GameState gameState;

    private String forkedWinner;

    private boolean isFork;

    private Cell forkedCell;


    private Map<String,Integer> playerMoves;

    public String getForkedWinner() {
        return forkedWinner;
    }

    public void setForkedWinner(String forkedWinner) {
        this.forkedWinner = forkedWinner;
    }

    public boolean isFork() {
        return isFork;
    }

    public void setFork(boolean fork) {
        isFork = fork;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }


    public Map<String, Integer> getPlayerMoves() {
        return playerMoves;
    }

    public void setPlayerMoves(Map<String, Integer> playerMoves) {
        this.playerMoves = playerMoves;
    }


    public Cell getForkedCell() {
        return forkedCell;
    }

    public void setForkedCell(Cell forkedCell) {
        this.forkedCell = forkedCell;
    }
    
    public GameInfo(String forkedWinner,boolean isFork,Map<String,Integer> playerMoves,GameState gameState){
        this.isFork = isFork;
        this.forkedWinner = forkedWinner;
        this.playerMoves = playerMoves;
        this.gameState = gameState;
    }

    public GameInfo(String forkedWinner,boolean isFork,Map<String,Integer> playerMoves,GameState gameState,Cell forkedCell){
        this.isFork = isFork;
        this.forkedWinner = forkedWinner;
        this.playerMoves = playerMoves;
        this.gameState = gameState;
        this.forkedCell = forkedCell;
    }
}
