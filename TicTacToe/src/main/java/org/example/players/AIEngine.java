package org.example.players;


import org.example.game.Board;
import org.example.game.Move;
import org.example.boards.TicTacToeBoard;

public class AIEngine extends Entity {
    public AIEngine(String name,String playerSymbol){
        super(name,playerSymbol);
    }

    public Move makeRandomMove(Board board) throws Exception {
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard)board;
            return ticTacToeBoard.randomMove(this);
        }
        else {
            throw new Exception("Invalid Board Move");
        }
    }

    public Move makeSmartMove(Board board) throws Exception{
            return board.smartMove(this);
    }
}