package org.example;


import org.example.boards.TicTacToeBoard;

public class GameEngine {
    public GameState move(Board board,Move move) throws Exception {
        board.move(move);
        return board.getState();
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