package org.example.boards;


import org.example.*;
import org.example.cells.TicTacToeCell;

public class TicTacToeBoard implements Board {

    private String cells[][];


    public Player getPreviousPlayer() {
        return previousPlayer;
    }

    public void setPreviousPlayer(Player previousPlayer) {
        this.previousPlayer = previousPlayer;
    }

    private Player previousPlayer;

    public TicTacToeBoard(){
        cells = new String[3][3];
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells.length;j++){
                cells[i][j]="-";
            }
        }
    }
    public void move(Move move) throws Exception {
        Player player = move.getPlayer();
        if(this.getPreviousPlayer()!=null && player.equals(previousPlayer)){
            throw new Exception("SamePlayer played twice");
        }
        else {
            this.setPreviousPlayer(player);
        }
        Cell cell = move.getCell();
        if(!cell.validate()){
            throw new Exception("Invalid Cell");
        }
        if (cell instanceof TicTacToeCell) {
            TicTacToeCell ticTacToeCell = (TicTacToeCell) cell;
            int row = ticTacToeCell.getRow();
            int col = ticTacToeCell.getCol();
           if (!cells[row][col].equals("-")) {
                throw new Exception("Invalid move!");
           }
           else {
                cells[row][col] = player.getPlayerSymbol();
           }
        } else {
            throw new Exception("Invalid Cell");
        }
    }

    public GameState getState(){
        // row check and col check and diagonal check and rev diag check
        boolean isWon = false;
        String playerSymbol = "-";
        for(int i=0;i<3;i++){
            boolean isWin = true;
            String firstChar = cells[i][0];
            if(firstChar.equals("-")){continue;}
            for(int j=1;j<3;j++){
                if(!firstChar.equals(cells[i][j])){
                    isWin = false;
                    break;
                }
            }
            if(isWin){
                isWon = isWin;
                playerSymbol = firstChar;
            }
        }
        for(int i=0;i<3;i++){
            boolean isWin = true;
            String firstChar = cells[i][0];
            if(firstChar.equals("-")){continue;}
            for(int j=1;j<3;j++){
                if(!firstChar.equals(cells[j][i])){
                    isWin = false;
                    break;
                }
            }
            if(isWin){
                isWon = isWin;
                playerSymbol = firstChar;
            }
        }
        String firstChar = cells[0][0];
        boolean isDiag = !firstChar.equals("-");
        for(int i=0;i<3;i++){
            if(!firstChar.equals(cells[i][i])){
                isDiag = false;
                break;
            }
        }
        if(isDiag){
            isWon = true;
            playerSymbol = firstChar;
        }
        firstChar = cells[0][2];
        boolean isRevDiag = !firstChar.equals("-");
        for(int i=0;i<3;i++){
            if(!firstChar.equals(cells[i][2-i])){
                isRevDiag = false;
                break;
            }
        }
        if(isRevDiag){
            isWon = true;
            playerSymbol = firstChar;
        }
        return new GameState(isWon,playerSymbol);
    }
}