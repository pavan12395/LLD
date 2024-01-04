package org.example.game;


import org.example.cells.Cell;
import org.example.players.Entity;

public abstract class Move {

    protected Entity entity;

    public Move(Entity entity){
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }


    public void setEntity(Entity entity) {
        this.entity = entity;
    }



}