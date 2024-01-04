package org.example.rulengines;

import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.GameState;
import org.example.players.Entity;

public abstract class RuleEngine {

    private Entity previousEntity;


    public Entity getPreviousEntity() {
        return previousEntity;
    }

    public void setPreviousEntity(Entity previousEntity) {
        this.previousEntity = previousEntity;
    }


    protected boolean isEqualsPreviousEntity(Entity entity) {
        return this.previousEntity==null ? false : this.previousEntity.equals(entity);
    }

    protected abstract boolean validate(Cell cell) throws Exception;

    public abstract boolean checkMove(Move move, Board board) throws Exception;

    public abstract GameState getState(Board board) throws Exception;

}