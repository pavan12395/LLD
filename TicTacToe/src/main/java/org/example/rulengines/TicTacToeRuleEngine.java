package org.example.rulengines;


import org.example.cells.Cell;
import org.example.game.*;
import org.example.boards.TicTacToeBoard;
import org.example.iterators.ColIterator;
import org.example.iterators.DiagonalIterator;
import org.example.iterators.RowIterator;
import org.example.moves.TicTacToeMove;


import java.util.Iterator;

public class TicTacToeRuleEngine extends RuleEngine{

    private GameState checkIterator(Iterator<CellValue> iterator) throws Exception {
        String first = null;
        if(!iterator.hasNext()){
            throw new Exception("Empty Iterator");
        }
        else {
            first = iterator.next().getValue();
        }
        if(first.equals("-")){
            return new GameState(GameResult.PENDING,"-");
        }
        while(iterator.hasNext()){
            if(!iterator.next().getValue().equals(first)){
                return new GameState(GameResult.PENDING,"-");
            }
        }
        return new GameState(GameResult.OVER,first);
    }

    public GameState getState(Board board) throws Exception{
        if(board instanceof TicTacToeBoard) {
            String[][] cells = board.getCells();
            GameState gameState;
            for (int i = 0; i < 3; i++) {
                RowIterator rowIterator = new RowIterator(cells, i);
                gameState = checkIterator(rowIterator);
                if (gameState.getGameResult() == GameResult.OVER) {
                    return gameState;
                }
                ColIterator colIterator = new ColIterator(cells, i);
                gameState = checkIterator(colIterator);
                if (gameState.getGameResult() == GameResult.OVER) {
                    return gameState;
                }
            }
            DiagonalIterator diagonalIterator = new DiagonalIterator(cells, false);
            DiagonalIterator revDiagonalIterator = new DiagonalIterator(cells, true);
            gameState = checkIterator(diagonalIterator);
            if (gameState.getGameResult() == GameResult.OVER) {
                return gameState;
            }
            gameState = checkIterator(revDiagonalIterator);
            if (gameState.getGameResult() == GameResult.OVER) {
                return gameState;
            }
            return new GameState(GameResult.PENDING, "-");
        }
        else {
            throw new Exception("Invalid Board Exception");
        }
    }
    
    protected boolean validate(Cell cell){
            int row = cell.getRow();
            int col = cell.getCol();
            return row>=0 && col>=0 && col<=2 && row<=2;
    }
}