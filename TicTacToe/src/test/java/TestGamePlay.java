package test;


import org.example.game.BoardType;
import org.example.moves.TicTacToeMove;
import org.example.api.GameEngine;
import org.example.cells.Cell;
import org.example.game.Board;
import org.example.game.GameResult;
import org.example.game.GameState;
import org.example.players.AIEngine;
import org.example.players.Player;
import org.junit.Assert;
import org.junit.Test;

public class TestGamePlay{


    @Test
    public void TestInvalidCellMove(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player player = new Player("1","X");
            TicTacToeMove move = new TicTacToeMove(new Cell(-1,-1),player);
            gameEngine.move(board,move);
            Assert.assertTrue(false);
        }
        catch (Exception e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void TestOccupiedCellMove(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player player = new Player("1","X");
            Player player1 = new Player("1","O");
            TicTacToeMove move = new TicTacToeMove(new Cell(0,0),player);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(0,0),player1);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
            Assert.assertTrue(false);
        }
        catch (Exception e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void TestCompleteMoveByAIEngine(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player player = new Player("1","X");
            AIEngine aiEngine = new AIEngine("2","O");
            TicTacToeMove move = new TicTacToeMove(new Cell(0,0),aiEngine);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(1,0),player);
            TicTacToeMove move2 = new TicTacToeMove(new Cell(0,1),aiEngine);
            TicTacToeMove move3 = new TicTacToeMove(new Cell(2,0),player);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
            gameEngine.move(board,move2);
            TicTacToeMove move4 = (TicTacToeMove) aiEngine.makeSmartMove(board);
            Cell cell = move4.getCell();
            Assert.assertTrue(cell.getRow()==0 && cell.getCol()==2);
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }


    @Test
    public void TestBlockMoveByAIEngine(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player player = new Player("1","X");
            AIEngine aiEngine = new AIEngine("2","O");
            TicTacToeMove move = new TicTacToeMove(new Cell(0,0),player);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(1,0),aiEngine);
            TicTacToeMove move2 = new TicTacToeMove(new Cell(0,1),player);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
            gameEngine.move(board,move2);
            TicTacToeMove move3 = (TicTacToeMove) aiEngine.makeSmartMove(board);
            Cell cell = move3.getCell();
            Assert.assertTrue(cell.getRow()==0 && cell.getCol()==2);
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }
    @Test
    public void TestInvalidMoves(){
        try
        {
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            TicTacToeMove move = new TicTacToeMove(new Cell(0,0),firstPlayer);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(0,0),secondPlayer);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
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
            TicTacToeMove move = new TicTacToeMove(new Cell(0,0),firstPlayer);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(1,1),secondPlayer);
            TicTacToeMove move2 = new TicTacToeMove(new Cell(0,1),firstPlayer);
            TicTacToeMove move3 = new TicTacToeMove(new Cell(1,2),secondPlayer);
            TicTacToeMove move4 = new TicTacToeMove(new Cell(0,2),firstPlayer);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
            gameEngine.move(board,move2);
            gameEngine.move(board,move3);
            GameState gameState = gameEngine.move(board,move4);
            Assert.assertTrue(gameState.getGameResult() == GameResult.OVER && gameState.getWinner()== firstPlayer.getPlayerSymbol());
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }


    @Test
    public void TestMoveAfterGameOver(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            TicTacToeMove move = new TicTacToeMove(new Cell(0,0),firstPlayer);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(1,1),secondPlayer);
            TicTacToeMove move2 = new TicTacToeMove(new Cell(0,1),firstPlayer);
            TicTacToeMove move3 = new TicTacToeMove(new Cell(1,2),secondPlayer);
            TicTacToeMove move4 = new TicTacToeMove(new Cell(0,2),firstPlayer);
            TicTacToeMove move5 = new TicTacToeMove(new Cell(2,1),secondPlayer);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
            gameEngine.move(board,move2);
            gameEngine.move(board,move3);
            gameEngine.move(board,move4);
            GameState gameState = gameEngine.move(board,move5);
            Assert.assertTrue(false);
        }
        catch (Exception e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void TestFirstPlayerWonColumn(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            TicTacToeMove move = new TicTacToeMove(new Cell(0,0),firstPlayer);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(1,1),secondPlayer);
            TicTacToeMove move2 = new TicTacToeMove(new Cell(1,0),firstPlayer);
            TicTacToeMove move3 = new TicTacToeMove(new Cell(1,2),secondPlayer);
            TicTacToeMove move4 = new TicTacToeMove(new Cell(2,0),firstPlayer);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
            gameEngine.move(board,move2);
            gameEngine.move(board,move3);
            GameState gameState = gameEngine.move(board,move4);
            Assert.assertTrue(gameState.getGameResult() == GameResult.OVER && gameState.getWinner()== firstPlayer.getPlayerSymbol());
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
            TicTacToeMove move = new TicTacToeMove(new Cell(0,0),firstPlayer);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(1,0),secondPlayer);
            TicTacToeMove move2 = new TicTacToeMove(new Cell(1,1),firstPlayer);
            TicTacToeMove move3 = new TicTacToeMove(new Cell(1,2),secondPlayer);
            TicTacToeMove move4 = new TicTacToeMove(new Cell(2,2),firstPlayer);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
            gameEngine.move(board,move2);
            gameEngine.move(board,move3);
            GameState gameState =gameEngine.move(board,move4);
            Assert.assertTrue(gameState.getGameResult()== GameResult.OVER && gameState.getWinner()== firstPlayer.getPlayerSymbol());
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
            TicTacToeMove move = new TicTacToeMove(new Cell(0,2),firstPlayer);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(1,0),secondPlayer);
            TicTacToeMove move2 = new TicTacToeMove(new Cell(1,1),firstPlayer);
            TicTacToeMove move3 = new TicTacToeMove(new Cell(1,2),secondPlayer);
            TicTacToeMove move4 = new TicTacToeMove(new Cell(2,0),firstPlayer);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
            gameEngine.move(board,move2);
            gameEngine.move(board,move3);
            GameState gameState = gameEngine.move(board,move4);
            Assert.assertTrue(gameState.getGameResult()==GameResult.OVER && gameState.getWinner()== firstPlayer.getPlayerSymbol());
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TestMoreMovesRequired(){
        try{
            GameEngine gameEngine = new GameEngine();
            Board board = gameEngine.start(BoardType.TICTACTOE);
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            TicTacToeMove move = new TicTacToeMove(new Cell(0,0),firstPlayer);
            TicTacToeMove move1 = new TicTacToeMove(new Cell(0,2),secondPlayer);
            TicTacToeMove move2 = new TicTacToeMove(new Cell(0,1),firstPlayer);
            TicTacToeMove move3 = new TicTacToeMove(new Cell(1,0),secondPlayer);
            TicTacToeMove move4 = new TicTacToeMove(new Cell(1,2),firstPlayer);
            TicTacToeMove move5 = new TicTacToeMove(new Cell(1,1),secondPlayer);
            TicTacToeMove move6 = new TicTacToeMove(new Cell(2,0),firstPlayer);
            gameEngine.move(board,move);
            gameEngine.move(board,move1);
            gameEngine.move(board,move2);
            gameEngine.move(board,move3);
            gameEngine.move(board,move4);
            gameEngine.move(board,move5);
            GameState gameState = gameEngine.move(board,move6);
            Assert.assertTrue(gameState.getGameResult()!=GameResult.OVER && gameState.getWinner()== "-");
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }


}
