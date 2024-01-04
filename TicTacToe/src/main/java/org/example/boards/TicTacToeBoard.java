package org.example.boards;


import org.example.moves.TicTacToeMove;
import org.example.cells.TicTacToeCell;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.GameState;
import org.example.game.Move;
import org.example.players.Entity;
import org.example.rulengines.RuleEngine;
import org.example.rulengines.TicTacToeRuleEngine;

public class TicTacToeBoard implements Board {

    private String cells[][];



    public TicTacToeBoard(){
        cells = new String[3][3];
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells.length;j++){
                cells[i][j]="-";
            }
        }
    }

    public void setCell(Cell cell,String value) throws Exception {
        if(cell instanceof TicTacToeCell) {
            TicTacToeCell cell1 = (TicTacToeCell) cell;
            int row = cell1.getRow();
            int col = cell1.getCol();
            this.cells[row][col] = value;
        }
        else {
            throw new Exception("Invalid Cell!");
        }
    }

    public String getCell(Cell cell) throws  Exception{
        if(cell instanceof TicTacToeCell) {
            TicTacToeCell cell1 = (TicTacToeCell) cell;
            int row = cell1.getRow();
            int col = cell1.getCol();
            return this.cells[row][col];
        }
        else {
            throw new Exception("Invalid Cell!");
        }
    }

}