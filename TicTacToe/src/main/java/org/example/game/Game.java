package org.example.game;

import org.example.rulengines.RuleEngine;


public class Game {
    private String previousPlayerSymbol;

    private Board board;

    private RuleEngine ruleEngine;


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Game(Board board){
        this.previousPlayerSymbol = null;
        this.board = board;
        this.ruleEngine = new RuleEngine();
    }

    public void move(Move move) throws  Exception {
        GameState gameState = this.getState();
        if(gameState.getGameResult().equals(GameResult.OVER) || !this.validateMove(move)){
            throw new Exception("Invalid Move!");
        }
        board.setCell(move.getCell(), move.getEntity().getPlayerSymbol());
        this.previousPlayerSymbol = move.getEntity().getPlayerSymbol();
    }

    public GameInfo getInfo() throws Exception {
        return ruleEngine.getInfo(board);
    }

    public GameState getState(){
        return ruleEngine.getState(board);
    }

    private boolean validateMove(Move move) throws Exception{
            String currentPlayerSymbol = move.getEntity().getPlayerSymbol();
            return !currentPlayerSymbol.equals(previousPlayerSymbol) && move.getCell().validate() && !this.board.isOccupied(move.getCell());
    }


}