package org.example.placements;


import org.example.boards.Board;
import org.example.boards.TicTacToeBoard;
import org.example.cells.Cell;
import org.example.cells.TicTacToeCell;
import org.example.players.Entity;
import org.example.rulengines.RuleEngine;

import java.util.Optional;

public class CenterPlacement implements Placement {
    private static CenterPlacement instance;

    private int[] center = {1,1};

    public static CenterPlacement getInstance(){
        if(instance == null){
            synchronized (CenterPlacement.class){
                if(instance == null){
                    instance = new CenterPlacement();
                }
            }
        }
        return instance;
    }

    public Optional<Cell> getCell(Board board, Entity entity){
        Cell cell = null;
        try {
            if (board instanceof TicTacToeBoard) {
                TicTacToeBoard b = (TicTacToeBoard) board;
                TicTacToeCell ticTacToeCell = new TicTacToeCell(center[0],center[1]);
                if(b.isOccupied(cell)){
                    cell = null;
                }
                else {
                    cell = ticTacToeCell;
                }
            }
        }
        catch (Exception e){
            System.out.println("Exception in CenterPlacement : "+e);
        }
        finally {
            return Optional.of(cell);
        }
    }

    public Placement next(){
        return CornerPlacement.getInstance();
    }
}