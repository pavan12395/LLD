package org.example.placements;


import org.example.boards.Board;
import org.example.cells.Cell;
import org.example.players.Entity;

import java.util.Optional;

public interface Placement {
    public abstract Optional<Cell> getCell(Board board, Entity entity);

    public abstract Placement next();
}