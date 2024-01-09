package org.example.boards;


import org.example.cells.Cell;
import org.example.cells.TicTacToeCellValue;
import org.example.cells.TicTacToeCell;
import org.example.game.*;
import org.example.iterators.ColIterator;
import org.example.iterators.DiagonalIterator;
import org.example.iterators.RowIterator;
import org.example.placements.DefensivePlacement;
import org.example.placements.OffensivePlacement;
import org.example.players.Entity;
import org.example.players.Player;
import org.example.rulengines.Rule;
import org.example.rulengines.RuleEngine;

import java.util.*;

public class TicTacToeBoard implements CellBoard {

    private String cells[][];
    
    
    
    public static List<Rule> getStateRules(){
        List<Rule> rules = new ArrayList<>();
        for(int i=0;i<3;i++) {
            final int ii = i;
            Rule<TicTacToeBoard,GameState> rowRule = new Rule<>((TicTacToeBoard board) -> checkIterator(new RowIterator(board.getCells(), ii)));
            Rule<TicTacToeBoard,GameState> colRule = new Rule<>((TicTacToeBoard board)->checkIterator(new ColIterator(board.getCells(),ii)));
            rules.add(rowRule);
            rules.add(colRule);
        }
        Rule<TicTacToeBoard,GameState> diagonalRule = new Rule<>((TicTacToeBoard board)->checkIterator(new DiagonalIterator(board.getCells(),false)));
        Rule<TicTacToeBoard,GameState> revDiagonalRule = new Rule<>((TicTacToeBoard board)->checkIterator(new DiagonalIterator(board.getCells(),true)));
        Rule<TicTacToeBoard,GameState> allFilledRule = new Rule<>((TicTacToeBoard board)->checkAllFilledCondition(board));
        rules.add(diagonalRule);
        rules.add(revDiagonalRule);
        rules.add(allFilledRule);
        return rules;
    }

    public static List<Rule> getForkRules(RuleEngine ruleEngine){
        List<Rule> rules = new ArrayList<>();
        Rule<TicTacToeBoard,GameInfo> atLeastOneMoveRule = new Rule<>((TicTacToeBoard board)->checkForked(ruleEngine,board));
        rules.add(atLeastOneMoveRule);
        return rules;
    }

    private static GameInfo checkForked(RuleEngine ruleEngine,TicTacToeBoard board) {
        try {
            // fork for a player
            Player players[] = new Player[]{new Player("dummy", "X"), new Player("dummy", "O")};
            for (Player player : players) {
                TicTacToeBoard board1 = (TicTacToeBoard) board.copy();
                Player opponent = (Player)player.flip();
                TicTacToeCell defensive = (TicTacToeCell) DefensivePlacement.getInstance().getCell(board1,opponent).get();
                board1.setCell(defensive,opponent.getPlayerSymbol());
                TicTacToeCell offensive = (TicTacToeCell) OffensivePlacement.getInstance().getCell(board1,player).get();
                if(offensive!=null){
                    return new GameInfo(player.getPlayerSymbol(),true,board.getPlayerMoves(),ruleEngine.getState(board),offensive);
                }
            }
            return new GameInfo("-", false, board.getPlayerMoves(), ruleEngine.getState(board));
        }
        catch(Exception e) {
            return new GameInfo("-",false,board.getPlayerMoves(),new GameState(GameResult.PENDING,"-"));
        }
    }

    private static GameState checkAllFilledCondition(TicTacToeBoard ticTacToeBoard){
        return new GameState(ticTacToeBoard.isAllFilled() ? GameResult.OVER : GameResult.PENDING,"-");
    }


    private static GameState checkIterator(Iterator<TicTacToeCellValue> iterator){
        String first = null;
        if(!iterator.hasNext()){
            return new GameState(GameResult.PENDING,"-");
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


    public TicTacToeBoard(){
        cells = new String[3][3];
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells.length;j++){
                cells[i][j]="-";
            }
        }
    }

    public TicTacToeBoard(String cells[][]){
       this.cells = new String[3][3];
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               this.cells[i][j] = cells[i][j];
           }
       }
    }

    public void setCell(Cell cell,String value) throws  Exception {
        if(cell instanceof TicTacToeCell) {
            TicTacToeCell cell1 = (TicTacToeCell) cell;
            int row = cell1.getRow();
            int col = cell1.getCol();
            this.cells[row][col] = value;
        }
        else {
            throw new Exception("Invalid");
        }
    }

    public String getCell(Cell cell) throws Exception{
        if(cell instanceof TicTacToeCell) {
            TicTacToeCell cell1 = (TicTacToeCell) cell;
            int row = cell1.getRow();
            int col = cell1.getCol();
            return this.cells[row][col];
        }
        else {
            throw new Exception("Invalid");
        }
    }


    public boolean isOccupied(Cell cell) throws  Exception {
        if(cell instanceof TicTacToeCell) {
            TicTacToeCell cell1 = (TicTacToeCell) cell;
            return !this.cells[cell1.getRow()][cell1.getCol()].equals("-");
        }
        else {
            throw new Exception("Invalid");
        }
    }

    public Board copy(){
        return new TicTacToeBoard(cells);
    }

    public Map<String,Integer> getPlayerMoves() {
        Map<String,Integer> playerMoves = new HashMap<>();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                playerMoves.putIfAbsent(cells[i][j],0);
                playerMoves.put(cells[i][j],playerMoves.get(cells[i][j])+1);
            }
        }
        return playerMoves;
    }

    public String[][] getCells(){
        return this.cells;
    }

    public boolean isAllFilled(){
        String symbol = this.cells[0][0];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(cells[i][j].equals("-")){
                    return false;
                }
            }
        }
        return true;
    }



}