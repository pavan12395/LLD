package org.example.api;


import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.GameState;
import org.example.game.Move;
import org.example.boards.TicTacToeBoard;

public class GameEngine {
    public GameState move(Board board, Move move) throws Exception {
        board.move(move);
        return board.getState();
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