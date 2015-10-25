package demo.controllers;

public class UserModel{
    private String name;
    private int points;

    public UserModel(String name, int points) {
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

