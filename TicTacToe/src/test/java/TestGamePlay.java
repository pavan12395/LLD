


import org.example.boards.Board;
import org.example.cells.TicTacToeCell;
import org.example.game.*;
import org.example.api.GameEngine;
import org.example.cells.TicTacToeCell;
import org.example.placements.DefensivePlacement;
import org.example.placements.ForkPlacement;
import org.example.placements.OffensivePlacement;
import org.example.players.AIEngine;
import org.example.players.Player;
import org.junit.Assert;
import org.junit.Test;

public class TestGamePlay{


    @Test
    public void TestForkDefensivePlacement(){
        try {
            GameEngine gameEngine = new GameEngine();
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1", "X");
            Player secondPlayer = new Player("2", "O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(0,2),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(2,0),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(2,2),firstPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            TicTacToeCell cell = (TicTacToeCell) ForkPlacement.getInstance(true).getCell(game.getBoard(),secondPlayer).get();
            int row = cell.getRow();
            int col = cell.getCol();
            Assert.assertTrue((row==1 || row==2) && col==1);
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }


    @Test
    public void TestForkOffensivePlacement(){
        try {
            GameEngine gameEngine = new GameEngine();
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1", "X");
            Player secondPlayer = new Player("2", "O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(0,2),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(2,0),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(2,2),firstPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            TicTacToeCell cell = (TicTacToeCell) ForkPlacement.getInstance(false).getCell(game.getBoard(),firstPlayer).get();
            int row = cell.getRow();
            int col = cell.getCol();
            Assert.assertTrue((row==1 || row==2) && col==1);
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TestDefensivePlacement(){
        try {
            GameEngine gameEngine = new GameEngine();
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<>(new TicTacToeCell(0,0),firstPlayer,2);
            Move<TicTacToeCell> move1 = new Move<>(new TicTacToeCell(2,1),secondPlayer,3);
            Move<TicTacToeCell> move2 = new Move<>(new TicTacToeCell(1,1),firstPlayer,34);
            game.move(move);
            game.move(move1);
            game.move(move2);
            TicTacToeCell cell = (TicTacToeCell) DefensivePlacement.getInstance().getCell(game.getBoard(), secondPlayer).get();
            Assert.assertTrue(cell.getRow() == 2 && cell.getCol() == 2);
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }



    @Test
    public void TestOffensivePlacement(){
        try {
            GameEngine gameEngine = new GameEngine();
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<>(new TicTacToeCell(0,0),firstPlayer,2);
            Move<TicTacToeCell> move1 = new Move<>(new TicTacToeCell(2,1),secondPlayer,3);
            Move<TicTacToeCell> move2 = new Move<>(new TicTacToeCell(1,1),firstPlayer,34);
            game.move(move);
            game.move(move1);
            game.move(move2);
            TicTacToeCell cell = (TicTacToeCell) OffensivePlacement.getInstance().getCell(game.getBoard(), firstPlayer).get();
            Assert.assertTrue(cell.getRow() == 2 && cell.getCol() == 2);
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }



    @Test
    public void TestTimedOutPerMove(){
        Game game = null;
        Player secondPlayer = null;
        try {
            GameEngine gameEngine = new GameEngine();
            game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(true,true,10,100));
            Player firstPlayer = new Player("1","X");
            secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<>(new TicTacToeCell(0,0),firstPlayer,2);
            Move<TicTacToeCell> move1 = new Move<>(new TicTacToeCell(1,1),secondPlayer,3);
            Move<TicTacToeCell> move2 = new Move<>(new TicTacToeCell(2,2),firstPlayer,34);
            game.move(move);
            game.move(move1);
            game.move(move2);
            Assert.assertTrue(false);
        }
        catch (Exception e){
            GameResult result = game.getState().getGameResult();
            String winner = game.getState().getWinner();;
            Assert.assertTrue(result.equals(GameResult.TIMED_OUT) && winner.equals(secondPlayer.getPlayerSymbol()));
        }
    }


    @Test
    public void TestInvalidCellMove(){
        try{
            GameEngine gameEngine = new GameEngine();
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player player = new Player("1","X");
            Move<TicTacToeCell> move = new Move<>(new TicTacToeCell(-1,-1),player);
            gameEngine.move(game,move);
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player player = new Player("1","X");
            Player player1 = new Player("1","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),player);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(0,0),player1);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player player = new Player("1","X");
            AIEngine aiEngine = new AIEngine("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),aiEngine);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(1,0),player);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),aiEngine);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            Move<TicTacToeCell> move4 = (Move<TicTacToeCell>) aiEngine.makeSmartMove(game.getBoard());
            TicTacToeCell cell = move4.getCell();
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player player = new Player("1","X");
            AIEngine aiEngine = new AIEngine("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),player);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(1,0),aiEngine);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),player);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            Move<TicTacToeCell> move3 = (Move<TicTacToeCell>) aiEngine.makeSmartMove(game.getBoard());
            TicTacToeCell cell = move3.getCell();
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(0,0),secondPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(1,1),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(1,2),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(0,2),firstPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            GameState gameState = gameEngine.getGameState(game);
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(1,1),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(1,2),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(0,2),firstPlayer);
            Move<TicTacToeCell> move5 = new Move<TicTacToeCell>(new TicTacToeCell(2,1),secondPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            gameEngine.move(game,move5);
            GameState gameState = gameEngine.getGameState(game);
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(1,1),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(1,0),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(1,2),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(2,0),firstPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            GameState gameState = gameEngine.getGameState(game);
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(1,0),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(1,1),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(1,2),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(2,2),firstPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            GameState gameState =gameEngine.getGameState(game);
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,2),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(1,0),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(1,1),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(1,2),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(2,0),firstPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            GameState gameState = gameEngine.getGameState(game);
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
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(0,2),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(1,0),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(1,2),firstPlayer);
            Move<TicTacToeCell> move5 = new Move<TicTacToeCell>(new TicTacToeCell(1,1),secondPlayer);
            Move<TicTacToeCell> move6 = new Move<TicTacToeCell>(new TicTacToeCell(2,0),firstPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            gameEngine.move(game,move5);
            gameEngine.move(game,move6);
            GameState gameState = gameEngine.getGameState(game);
            Assert.assertTrue(gameState.getGameResult()!=GameResult.OVER && gameState.getWinner()== "-");
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TestGameInfoForked(){
        try {
            GameEngine gameEngine = new GameEngine();
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1", "X");
            Player secondPlayer = new Player("2", "O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(0,2),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(2,0),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(2,2),firstPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            GameInfo gameInfo = gameEngine.getGameInfo(game);
            Assert.assertTrue(gameInfo.isFork() && gameInfo.getForkedWinner()== firstPlayer.getPlayerSymbol());
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TestGameInfoNotForked(){
        try {
            GameEngine gameEngine = new GameEngine();
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1", "X");
            Player secondPlayer = new Player("2", "O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(1,0),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(2,0),secondPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            GameInfo gameInfo = gameEngine.getGameInfo(game);
            Assert.assertTrue(!gameInfo.isFork() && gameInfo.getForkedWinner().equals("-"));
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TestAllFilled(){
        try{
            GameEngine gameEngine = new GameEngine();
            Game game = gameEngine.start(BoardType.TICTACTOE,new GameConfig(false,false,0,0));
            Player firstPlayer = new Player("1","X");
            Player secondPlayer = new Player("2","O");
            Move<TicTacToeCell> move = new Move<TicTacToeCell>(new TicTacToeCell(0,0),firstPlayer);
            Move<TicTacToeCell> move1 = new Move<TicTacToeCell>(new TicTacToeCell(0,2),secondPlayer);
            Move<TicTacToeCell> move2 = new Move<TicTacToeCell>(new TicTacToeCell(1,1),firstPlayer);
            Move<TicTacToeCell> move3 = new Move<TicTacToeCell>(new TicTacToeCell(2,2),secondPlayer);
            Move<TicTacToeCell> move4 = new Move<TicTacToeCell>(new TicTacToeCell(1,2),firstPlayer);
            Move<TicTacToeCell> move5 = new Move<TicTacToeCell>(new TicTacToeCell(1,0),secondPlayer);
            Move<TicTacToeCell> move6 = new Move<TicTacToeCell>(new TicTacToeCell(2,0),firstPlayer);
            Move<TicTacToeCell> move7 = new Move<TicTacToeCell>(new TicTacToeCell(0,1),secondPlayer);
            Move<TicTacToeCell> move8 = new Move<TicTacToeCell>(new TicTacToeCell(2,1),firstPlayer);
            gameEngine.move(game,move);
            gameEngine.move(game,move1);
            gameEngine.move(game,move2);
            gameEngine.move(game,move3);
            gameEngine.move(game,move4);
            gameEngine.move(game,move5);
            gameEngine.move(game,move6);
            gameEngine.move(game,move7);
            gameEngine.move(game,move8);
            GameState gameState = gameEngine.getGameState(game);
            Assert.assertTrue(gameState.getGameResult()==GameResult.OVER && gameState.getWinner()== "-");
        }
        catch (Exception e){
            Assert.assertTrue(false);
        }
    }

}
