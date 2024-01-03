package org.example.boards;


import org.example.TicTacToeMove;
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


    private RuleEngine ruleEngine;


    public TicTacToeBoard(){
        cells = new String[3][3];
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells.length;j++){
                cells[i][j]="-";
            }
        }
        this.ruleEngine = new TicTacToeRuleEngine();
    }
    public void move(Move move) throws Exception {
        if(move instanceof TicTacToeMove){
            if(ruleEngine.checkMove(move,this)){
                TicTacToeMove move1 = ((TicTacToeMove) move);
                TicTacToeCell cell = move1.getCell();
                int row = cell.getRow();
                int col = cell.getCol();
                cells[row][col] = move.getEntity().getPlayerSymbol();
                ruleEngine.setPreviousEntity(move.getEntity());
            }
            else {
                throw new Exception("Invalid Move");
            }
        }
        else {
            throw new Exception("Invalid Move");
        }
    }

    public boolean isOccupied(Cell cell) throws Exception
    {
        if(cell instanceof TicTacToeCell){
            TicTacToeCell cell1 = (TicTacToeCell) cell;
            String value = this.cells[cell1.getRow()][cell1.getCol()];
            return value==null ? false : !value.equals("-");
        }
        throw new Exception("Invalid Move");
    }


    public GameState getState() throws Exception{
        return ruleEngine.getState(this);
    }

    public String[][] getCells(){
        return this.cells;
    }

    public Move randomMove(Entity entity) throws Exception{
        return ruleEngine.randomMove(this,entity);
    }

    public Move smartMove(Entity entity) throws Exception{
        return ruleEngine.smartMove(this,entity);

    }

}