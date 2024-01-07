package org.example.players;

public abstract class Entity {
    private String playerSymbol;

    private String name;

    private long lastPlayedTimeStamp;

    private long totalTimeSpent;


    public Entity(String name,String playerSymbol){
        this.name = name;
        this.playerSymbol = playerSymbol;
        this.lastPlayedTimeStamp = 0;
        this.totalTimeSpent = 0;
    }


    public long getLastPlayedTimeStamp() {
        return lastPlayedTimeStamp;
    }

    public void setLastPlayedTimeStamp(long lastPlayedTimeStamp) {
        this.lastPlayedTimeStamp = lastPlayedTimeStamp;
    }


    public long getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(long totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }


    public void setPlayerSymbol(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Player player) {
        return this.name.equals(player.getName()) && this.playerSymbol.equals(player.getPlayerSymbol());
    }

    public Entity flip(){
        return new Player(this.name,this.playerSymbol.equals("X") ? "O" : "X");
    }
}