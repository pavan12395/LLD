package org.example.game;

import org.example.boards.Board;

import java.util.ArrayList;
import java.util.List;

public class History {
    List<Board> boards;

    public History(){
        boards = new ArrayList<>();
    }

    public Board undo() throws Exception {
        if(boards.size()==0)
        {
            boards.remove(boards.size() - 1);
            return boards.get(boards.size() - 1);
        }
        else {
            throw new Exception("No history recorded");
        }
    }
    public void add(Board board){
        boards.add(board);
    }

    public Board get(int index){
        return index>=boards.size() || index<0 ? null : boards.get(index);
    }

    public Board get(){
        return boards.get(boards.size()-1);
    }
}