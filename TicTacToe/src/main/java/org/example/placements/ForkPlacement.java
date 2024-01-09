package org.example.placements;


import org.example.boards.Board;
import org.example.cells.Cell;
import org.example.game.GameInfo;
import org.example.players.Entity;
import org.example.rulengines.RuleEngine;

import java.util.Optional;

public class ForkPlacement implements Placement {
    private static ForkPlacement defensive;

    private static ForkPlacement offensive;

    private RuleEngine ruleEngine;

    private boolean isDefensive;

    public ForkPlacement(boolean isDefensive){
        this.isDefensive = isDefensive;
        ruleEngine = new RuleEngine();
    }


    public static ForkPlacement getInstance(boolean isDefensive){
        if(isDefensive){
            if(defensive == null){
                synchronized (ForkPlacement.class){
                    if(defensive == null) {
                        defensive = new ForkPlacement(isDefensive);
                    }
                }
            }
            return defensive;
        }
        else {
            if(offensive == null){
                synchronized (ForkPlacement.class){
                    if(offensive == null){
                        offensive = new ForkPlacement(isDefensive);
                    }
                }
            }
            return offensive;
        }
    }

    public Optional<Cell> getCell(Board board, Entity entity){
        Cell cell = null;
        String symbol = isDefensive ? entity.flip().getPlayerSymbol() : entity.getPlayerSymbol();
        try {
            GameInfo info = ruleEngine.getInfo(board);
            if(info.isFork()){
                String forkedWinner = info.getForkedWinner();
                if(forkedWinner.equals(symbol)){
                    cell = info.getForkedCell();
                }
            }
        }
        catch (Exception e){
            System.out.println("Exception in ForkPlacement : "+e);
        }
        return Optional.of(cell);
    }

    public Placement next(){
        return isDefensive ? CenterPlacement.getInstance() : ForkPlacement.getInstance(true);
    }
}