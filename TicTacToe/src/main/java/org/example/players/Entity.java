package org.example.players;

public abstract class Entity {
    private String playerSymbol;

    private String name;

    public Entity(String name,String playerSymbol){
        this.name = name;
        this.playerSymbol = playerSymbol;
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

    public String flip(){
        return this.playerSymbol.equals("X") ? "O" : "X";
    }
}