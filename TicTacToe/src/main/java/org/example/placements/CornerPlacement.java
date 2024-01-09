package org.example.placements;


import org.example.boards.Board;
import org.example.boards.TicTacToeBoard;
import org.example.cells.Cell;
import org.example.cells.TicTacToeCell;
import org.example.players.Entity;
import org.example.rulengines.RuleEngine;

import java.util.Optional;

public class CornerPlacement implements Placement {
    private static CornerPlacement instance;

    private int[][] corners = {{0,0},{2,2},{0,2},{2,0}};

    public static CornerPlacement getInstance(){
        if(instance == null){
            synchronized (CornerPlacement.class){
                if(instance == null){
                    instance = new CornerPlacement();
                }
            }
        }
        return instance;
    }

    public Optional<Cell> getCell(Board board, Entity entity){
        Cell cell = null;
        try {
            if(board instanceof TicTacToeBoard) {
                for (int corner[] : corners) {
                    TicTacToeCell ticTacToeCell = new TicTacToeCell(corner[0], corner[1]);
                    if (!board.isOccupied(ticTacToeCell)) {
                        cell = ticTacToeCell;
                        break;
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println("Error in CornerPlacement : "+e);
        }
        finally {
            return Optional.of(cell);
        }
    }

    public Placement next(){
        return null;
    }
}