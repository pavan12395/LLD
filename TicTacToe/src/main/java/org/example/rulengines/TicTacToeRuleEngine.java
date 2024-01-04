package org.example.rulengines;


import org.example.cells.Cell;
import org.example.game.*;
import org.example.boards.TicTacToeBoard;
import org.example.iterators.ColIterator;
import org.example.iterators.DiagonalIterator;
import org.example.iterators.RowIterator;
import org.example.moves.TicTacToeMove;


import java.util.ArrayList;
import java.util.Iterator;

public class TicTacToeRuleEngine extends RuleEngine{

    public TicTacToeRuleEngine(){
        this.rules = new ArrayList<>();
        for(int i=0;i<3;i++) {
            final int ii = i;
            Rule rowRule = new Rule((Board board) -> checkIterator(new RowIterator(board.getCells(), ii)));
            Rule colRule = new Rule((Board board)->checkIterator(new ColIterator(board.getCells(),ii)));
            this.rules.add(rowRule);
            this.rules.add(colRule);
        }
        Rule diagonalRule = new Rule((Board board)->checkIterator(new DiagonalIterator(board.getCells(),false)));
        Rule revDiagonalRule = new Rule((Board board)->checkIterator(new DiagonalIterator(board.getCells(),true)));
        this.rules.add(diagonalRule);
        this.rules.add(revDiagonalRule);
    }

    private String checkIterator(Iterator<CellValue> iterator) {
        String first = null;
        if(!iterator.hasNext()){
            return "-";
        }
        else {
            first = iterator.next().getValue();
        }
        if(first.equals("-")){
            return "-";
        }
        while(iterator.hasNext()){
            if(!iterator.next().getValue().equals(first)){
                return "-";
            }
        }
        return first;
    }

    public GameState getState(Board board) throws Exception{
        for(Rule rule : this.rules){
            String winner = rule.getResult(board);
            if(!winner.equals("-")){
                return new GameState(GameResult.OVER,winner);
            }
        }
        return new GameState(GameResult.PENDING,"-");
    }
}