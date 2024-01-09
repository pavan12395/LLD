package org.example.players;


import javafx.util.Pair;
import org.example.cells.TicTacToeCell;
import org.example.boards.Board;
import org.example.cells.TicTacToeCellValue;
import org.example.game.Move;
import org.example.boards.TicTacToeBoard;
import org.example.iterators.ColIterator;
import org.example.iterators.DiagonalIterator;
import org.example.iterators.RowIterator;
import org.example.placements.OffensivePlacement;
import org.example.placements.Placement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class AIEngine extends Entity {
    public AIEngine(String name,String playerSymbol){
        super(name,playerSymbol);
    }


    public Move makeOptimalMove(){
        Placement placement = OffensivePlacement.getInstance();
        return null;
    }

    public Move makeRandomMove(Board board, Entity entity) throws Exception {
        Random random = new Random();
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            TicTacToeCell cell = new TicTacToeCell(0,0);
            while(!board1.isOccupied(cell)){
                cell.setRow(random.nextInt(3));
                cell.setCol(random.nextInt(3));
            }
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(entity,cell);
            return move;
        }
        else {
            throw new Exception("Invalid Board Exception");
        }
    }

    private Move getPossibleBlockOrComplete(Map<String,Integer> counts, Entity entity, TicTacToeCell vacantCell) {
        String symbol = entity.getPlayerSymbol();
        if(counts.containsKey(symbol) && counts.get(symbol)==2){
            return new Move<TicTacToeCell>(entity,vacantCell);
        }
        String oppSymbol = entity.flip().getPlayerSymbol();
        if(counts.containsKey(oppSymbol) && counts.get(oppSymbol)==2){
            return new Move<TicTacToeCell>(entity,vacantCell);
        }
        return null;
    }

    private Pair<Map<String,Integer>,TicTacToeCell> getCountAndVacantCellFromIterator(Iterator<TicTacToeCellValue> iterator){
        Map<String,Integer> count = new HashMap<>();
        TicTacToeCell vacantCell = null;
        while(iterator.hasNext()){
            TicTacToeCellValue curr = iterator.next();
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


    private Move getPossibleMoveFromIterator(Iterator<TicTacToeCellValue> iterator,Entity entity){
        Pair<Map<String,Integer>,TicTacToeCell> pair = this.getCountAndVacantCellFromIterator(iterator);
        Move<TicTacToeCell> move = this.getPossibleBlockOrComplete(pair.getKey(),entity,pair.getValue());
        return move;
    }

    public Move makeSmartMove(Board board) throws Exception{
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard)board;
            Move<TicTacToeCell> move = null;
            String[][] cells = board1.getCells();
                for (int i = 0; i < 3; i++) {
                    RowIterator rowIterator = new RowIterator(cells, i);
                    move = this.getPossibleMoveFromIterator(rowIterator, this);
                    if (move != null) {
                        return move;
                    }
                    ColIterator colIterator = new ColIterator(cells, i);
                    move = this.getPossibleMoveFromIterator(colIterator, this);
                    if (move != null) {
                        return move;
                    }
                }
                DiagonalIterator diagonalIterator = new DiagonalIterator(cells, false);
                move = this.getPossibleMoveFromIterator(diagonalIterator, this);
                if (move != null) {
                    return move;
                }
                DiagonalIterator revDiagonalIterator = new DiagonalIterator(cells, false);
                move = this.getPossibleMoveFromIterator(revDiagonalIterator, this);
                return move;
        }
        else {
            throw new Exception("Invalid Board Exception");
        }
    }
}

/*
getOptimalMove

Winning move
Block move
your fork
opp fork
center available
corner available
 */