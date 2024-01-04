package org.example.rulengines;


import org.example.game.*;
import org.example.moves.TicTacToeMove;
import org.example.boards.TicTacToeBoard;
import org.example.cells.TicTacToeCell;
import org.example.iterators.ColIterator;
import org.example.iterators.DiagonalIterator;
import org.example.iterators.RowIterator;
import org.example.players.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.util.Iterator;
import javafx.util.Pair;

public class TicTacToeRuleEngine extends RuleEngine{
    public boolean checkMove(Move move, Board board) throws  Exception {
        return !isEqualsPreviousEntity(move.getEntity()) &&
                validate(move.getCell()) && !board.isOccupied(move.getCell()) &&
                getState(board).getGameResult()!=GameResult.OVER;
    }

    private Pair<Map<String,Integer>,TicTacToeCell> getCountAndVacantCellFromIterator(Iterator<CellValue> iterator){
        Map<String,Integer> count = new HashMap<>();
        TicTacToeCell vacantCell = null;
        while(iterator.hasNext()){
            CellValue curr = iterator.next();
            String value = curr.getValue();
            if(!value.equals("-")) {
                count.putIfAbsent(value, 0);
                count.put(value, count.get(value) + 1);
            }
            else {
                vacantCell = new TicTacToeCell(curr.getRow(),curr.getCol());
            }
        }
        Pair<Map<String,Integer>,TicTacToeCell> pair = new Pair<>(count,vacantCell);
        return pair;
    }

    private GameState checkIterator(Iterator<CellValue> iterator) {
        Pair<Map<String,Integer>,TicTacToeCell> pair = this.getCountAndVacantCellFromIterator(iterator);
        Map<String,Integer> count = pair.getKey();
        if(count.size()==1){
            boolean isWon = false;
            String winner = "-";
            for(String key : count.keySet()){
                if(count.get(key) == 3){
                    isWon = true;
                    winner = key;
                }
            }
            return isWon ? new GameState(GameResult.OVER,winner) : new GameState(GameResult.PENDING,"-");
        }else {
            return new GameState(GameResult.PENDING,"-");
        }
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
    
    protected boolean validate(Cell cell) throws Exception{
        if(cell instanceof TicTacToeCell){
            TicTacToeCell cell1 = (TicTacToeCell) cell;
            int row = cell1.getRow();
            int col = cell1.getCol();
            return row>=0 && col>=0 && col<=2 && row<=2;
        }
        else {
            throw new Exception("Invalid Cell");
        }

    }

    public Move randomMove(Board board, Entity entity) throws Exception {
        Random random = new Random();
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            TicTacToeCell cell = new TicTacToeCell(0,0);
            while(!board1.isOccupied(cell)){
                cell.setRow(random.nextInt(3));
                cell.setCol(random.nextInt(3));
            }
            TicTacToeMove move = new TicTacToeMove(cell,entity);
            return move;
        }
        else {
            throw new Exception("Invalid Board Exception");
        }
    }

    private Move getPossibleBlockOrComplete(Map<String,Integer> counts,Entity entity,TicTacToeCell vacantCell) {
        String symbol = entity.getPlayerSymbol();
        if(counts.containsKey(symbol) && counts.get(symbol)==2){
           return new TicTacToeMove(vacantCell,entity);
        }
        String oppSymbol = entity.flip();
        if(counts.containsKey(oppSymbol) && counts.get(oppSymbol)==2){
            return new TicTacToeMove(vacantCell,entity);
        }
        return null;
    }
    
    public Move smartMove(Board board,Entity entity) throws Exception{
        Map<String,Integer> counts;
        Move move=null;
        Pair<Map<String,Integer>,TicTacToeCell> pair;
        String[][] cells = board.getCells();
        if(board instanceof TicTacToeBoard){
            for(int i=0;i<3;i++){
                RowIterator rowIterator = new RowIterator(cells,i);
                pair = this.getCountAndVacantCellFromIterator(rowIterator);
                move = this.getPossibleBlockOrComplete(pair.getKey(),entity,pair.getValue());
                if(move!=null){
                    return move;
                }
                ColIterator colIterator = new ColIterator(cells,i);
                pair = this.getCountAndVacantCellFromIterator(rowIterator);
                move = this.getPossibleBlockOrComplete(pair.getKey(),entity,pair.getValue());
                if(move!=null){
                    return move;
                }
            }
            DiagonalIterator diagonalIterator = new DiagonalIterator(cells,false);
            pair = this.getCountAndVacantCellFromIterator(diagonalIterator);
            move = this.getPossibleBlockOrComplete(pair.getKey(),entity, pair.getValue());
            if(move!=null){
                return move;
            }
            DiagonalIterator revDiagonalIterator = new DiagonalIterator(cells,false);
            pair = this.getCountAndVacantCellFromIterator(revDiagonalIterator);
            move = this.getPossibleBlockOrComplete(pair.getKey(),entity, pair.getValue());
            return move;
        }
        else {
            throw new Exception("Invalid Board Exception");
        }
    }
}