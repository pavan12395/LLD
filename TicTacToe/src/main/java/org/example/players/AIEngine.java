package org.example.players;


import javafx.util.Pair;
import org.example.cells.Cell;
import org.example.game.Board;
import org.example.game.CellValue;
import org.example.game.Move;
import org.example.boards.TicTacToeBoard;
import org.example.iterators.ColIterator;
import org.example.iterators.DiagonalIterator;
import org.example.iterators.RowIterator;
import org.example.moves.TicTacToeMove;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class AIEngine extends Entity {
    public AIEngine(String name,String playerSymbol){
        super(name,playerSymbol);
    }

    public Move makeRandomMove(Board board, Entity entity) throws Exception {
        Random random = new Random();
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Cell cell = new Cell(0,0);
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

    private Move getPossibleBlockOrComplete(Map<String,Integer> counts, Entity entity, Cell vacantCell) {
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

    private Pair<Map<String,Integer>,Cell> getCountAndVacantCellFromIterator(Iterator<CellValue> iterator){
        Map<String,Integer> count = new HashMap<>();
        Cell vacantCell = null;
        while(iterator.hasNext()){
            CellValue curr = iterator.next();
            String value = curr.getValue();
            if(!value.equals("-")) {
                count.putIfAbsent(value, 0);
                count.put(value, count.get(value) + 1);
            }
            else {
                vacantCell = new Cell(curr.getRow(),curr.getCol());
            }
        }
        Pair<Map<String,Integer>,Cell> pair = new Pair<>(count,vacantCell);
        return pair;
    }


    private Move getPossibleMoveFromIterator(Iterator<CellValue> iterator,Entity entity){
        Pair<Map<String,Integer>,Cell> pair = this.getCountAndVacantCellFromIterator(iterator);
        Move move = this.getPossibleBlockOrComplete(pair.getKey(),entity,pair.getValue());
        return move;
    }

    public Move makeSmartMove(Board board) throws Exception{
        Move move=null;
        String[][] cells = board.getCells();
        if(board instanceof TicTacToeBoard){
            for(int i=0;i<3;i++){
                RowIterator rowIterator = new RowIterator(cells,i);
                move = this.getPossibleMoveFromIterator(rowIterator,this);
                if(move!=null){
                    return move;
                }
                ColIterator colIterator = new ColIterator(cells,i);
                move = this.getPossibleMoveFromIterator(colIterator,this);
                if(move!=null){
                    return move;
                }
            }
            DiagonalIterator diagonalIterator = new DiagonalIterator(cells,false);
            move = this.getPossibleMoveFromIterator(diagonalIterator,this);
            if(move!=null){
                return move;
            }
            DiagonalIterator revDiagonalIterator = new DiagonalIterator(cells,false);
            move = this.getPossibleMoveFromIterator(revDiagonalIterator,this);
            return move;
        }
        else {
            throw new Exception("Invalid Board Exception");
        }
    }



}