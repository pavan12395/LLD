package test;


import org.example.*;
import org.example.cells.TicTacToeCell;
import org.junit.Assert;
import org.junit.Test;

public class TestGamePlay{
    @Test
    public void TestInvalidMoves(){
        try
        {
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move move = new Move(firstPlayer,new TicTacToeCell(0,0));
            Move move1 = new Move(secondPlayer,new TicTacToeCell(0,0));
            board.move(move);
            board.move(move1);
            Assert.assertTrue(false);
        }
        catch(Exception e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void TestFirstPlayerWonRow(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move move = new Move(firstPlayer,new TicTacToeCell(0,0));
            Move move1 = new Move(secondPlayer,new TicTacToeCell(1,1));
            Move move2 = new Move(firstPlayer,new TicTacToeCell(0,1));
            Move move3 = new Move(secondPlayer,new TicTacToeCell(1,2));
            Move move4 = new Move(firstPlayer,new TicTacToeCell(0,2));
            Move move5 = new Move(secondPlayer,new TicTacToeCell(2,1));
            board.move(move);
            board.move(move1);
            board.move(move2);
            board.move(move3);
            board.move(move4);
            board.move(move5);
            GameState gameState = board.getState();
            Assert.assertTrue(gameState.isOver() && gameState.getWinner()== firstPlayer.getPlayerSymbol());
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TestFirstPlayerWonColumn(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move move = new Move(firstPlayer,new TicTacToeCell(0,0));
            Move move1 = new Move(secondPlayer,new TicTacToeCell(1,1));
            Move move2 = new Move(firstPlayer,new TicTacToeCell(1,0));
            Move move3 = new Move(secondPlayer,new TicTacToeCell(1,2));
            Move move4 = new Move(firstPlayer,new TicTacToeCell(2,0));
            Move move5 = new Move(secondPlayer,new TicTacToeCell(2,1));
            board.move(move);
            board.move(move1);
            board.move(move2);
            board.move(move3);
            board.move(move4);
            board.move(move5);
            GameState gameState = board.getState();
            Assert.assertTrue(gameState.isOver() && gameState.getWinner()== firstPlayer.getPlayerSymbol());
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TestFirstPlayerWonDiagonal(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move move = new Move(firstPlayer,new TicTacToeCell(0,0));
            Move move1 = new Move(secondPlayer,new TicTacToeCell(1,0));
            Move move2 = new Move(firstPlayer,new TicTacToeCell(1,1));
            Move move3 = new Move(secondPlayer,new TicTacToeCell(1,2));
            Move move4 = new Move(firstPlayer,new TicTacToeCell(2,2));
            board.move(move);
            board.move(move1);
            board.move(move2);
            board.move(move3);
            board.move(move4);
            GameState gameState = board.getState();
            Assert.assertTrue(gameState.isOver() && gameState.getWinner()== firstPlayer.getPlayerSymbol());
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TestFirstPlayerWonRevDiagonal(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move move = new Move(firstPlayer,new TicTacToeCell(0,2));
            Move move1 = new Move(secondPlayer,new TicTacToeCell(1,0));
            Move move2 = new Move(firstPlayer,new TicTacToeCell(1,1));
            Move move3 = new Move(secondPlayer,new TicTacToeCell(1,2));
            Move move4 = new Move(firstPlayer,new TicTacToeCell(2,0));
            board.move(move);
            board.move(move1);
            board.move(move2);
            board.move(move3);
            board.move(move4);
            GameState gameState = board.getState();
            Assert.assertTrue(gameState.isOver() && gameState.getWinner()== firstPlayer.getPlayerSymbol());
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }


}