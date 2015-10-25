package demo.controllers;

public class PlayerModel{
    private String name;
    private int points;
    private double percentage;

    public PlayerModel(String name, int points) {
        this.name = name;
        this.points = points;

    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
