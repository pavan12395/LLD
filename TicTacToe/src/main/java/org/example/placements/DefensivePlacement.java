package org.example.placements;


import org.example.boards.Board;
import org.example.boards.TicTacToeBoard;
import org.example.cells.Cell;
import org.example.cells.TicTacToeCell;
import org.example.game.GameResult;
import org.example.game.GameState;
import org.example.players.Entity;
import org.example.rulengines.RuleEngine;

import java.util.Optional;

public class DefensivePlacement implements Placement {
    private static DefensivePlacement defensivePlacement;

    private RuleEngine ruleEngine;

    public DefensivePlacement(){this.ruleEngine = new RuleEngine();}

    public static DefensivePlacement getInstance(){
        if(defensivePlacement == null){
            synchronized (DefensivePlacement.class){
                if(defensivePlacement == null){
                    defensivePlacement = new DefensivePlacement();
                }
            }
        }
        return defensivePlacement;
    }

    private boolean checkStateForBoard(Board b,Cell cell,Entity entity){
        boolean ans = false;
        try {
            if(!b.isOccupied(cell)){
                Board b1 = b.copy();
                b1.setCell(cell,entity.getPlayerSymbol());
                GameState state = ruleEngine.getState(b1);
                if(state.getWinner().equals(entity.getPlayerSymbol()) && state.getGameResult() == GameResult.OVER){
                    ans = true;
                }
            }
        }
        catch (Exception e){
            System.out.println("error in OffensivePlacement : "+e);
        }
        return ans;
    }

    private Cell checkForTicTacToeBoard(TicTacToeBoard board,Entity entity){
        Cell cell = null;
        outer: for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TicTacToeCell ticTacToeCell = new TicTacToeCell(i, j);
                if (checkStateForBoard(board,ticTacToeCell,entity)){
                    cell = ticTacToeCell;
                    break outer;
                }
            }
        }
        return cell;
    }

    public Optional<Cell> getCell(Board board, Entity entity){
        Cell cell = null;
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard b = (TicTacToeBoard) board;
            cell = checkForTicTacToeBoard(b,entity.flip());
        }
        return Optional.of(cell);
    }

    public Placement next(){
        return ForkPlacement.getInstance(false);
    }
}