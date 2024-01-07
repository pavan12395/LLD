package org.example.game;


import org.example.cells.Cell;
import org.example.players.Entity;

public class Move<T extends Cell> {

    private Entity entity;

    private T cell;

    private long timeStamp;

    public Move(Entity entity,T cell,long timeStamp){
        this.entity = entity;
        this.cell = cell;
        this.timeStamp = timeStamp;
    }

    public Move(T cell,Entity entity,long timeStamp){
        this.cell = cell;
        this.entity = entity;
        this.timeStamp = timeStamp;
    }

    public Move(T cell,Entity entity){
        this.cell = cell;
        this.entity = entity;
        this.timeStamp = 0;
    }

    public Move(Entity entity,T cell){
        this.entity = entity;
        this.cell = cell;
        this.timeStamp = 0;
    }

    public Entity getEntity() {
        return entity;
    }


    public void setEntity(Entity entity) {
        this.entity = entity;
    }


    public T getCell(){return this.cell;}

    public void setCell(T cell){
        this.cell = cell;
    }

    public long getTimeStamp(){
        return this.timeStamp;
    }

}