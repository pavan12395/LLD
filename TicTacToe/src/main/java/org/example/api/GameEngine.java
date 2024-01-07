package org.example.api;


import org.example.game.*;
import org.example.boards.TicTacToeBoard;

public class GameEngine {
    public GameEngine(){
    }

    public GameState getGameState(Game game) {
        return game.getState();
    }

    public GameInfo getGameInfo(Game game) throws  Exception{
        return game.getInfo();
    }
    public void move(Game game,Move move) throws Exception {
        game.move(move);
    }

    public Game start(BoardType boardType) throws Exception {
        if(boardType == BoardType.TICTACTOE) {
            Board board = new TicTacToeBoard();
            return new Game(board);
        }
        else {
            throw new Exception("Invalid Board!");
        }
    }
}