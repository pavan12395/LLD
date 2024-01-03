package org.example.game;


import org.example.game.Cell;
import org.example.players.Entity;

public abstract class Move {

    protected Entity entity;
    protected Cell cell;

    public Move(Cell cell,Entity entity){
        this.cell = cell;
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }


    public void setEntity(Entity entity) {
        this.entity = entity;
    }


    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) throws Exception {
        this.cell = cell;
    }



}