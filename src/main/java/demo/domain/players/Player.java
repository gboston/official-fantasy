package demo.domain.players;

import demo.domain.matchdaypoints.MatchDayPoints;

import javax.persistence.*;
import java.util.List;

/**
 * Created by glenn on 9/22/2015.
 */
@Entity
public class Player {
    @Id
    private String name;
    private String lastName;
    private int age;
    private String team;
    private String picture;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<MatchDayPoints> matchDayPoints;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.team = team;
        this.picture = picture;
    }

    public MatchDayPoints getPointsByDay(int day){
        return matchDayPoints.stream().filter(x ->x.getDay()==day).findFirst().get();
    }

    public int getPoints(){
        return matchDayPoints.stream().mapToInt(x -> x.getPoints()).sum();
    }

    public void addMathDayPoints(MatchDayPoints points){
        matchDayPoints.add(points);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", matchDayPoints=" + matchDayPoints +
                '}';
    }
}

