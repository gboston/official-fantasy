package demo.domain.users;

import demo.domain.players.Player;
import demo.domain.players.TeamPlayer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 9/22/2015.
 */
@Entity
public class User {
    @Id
    private String name;

    private String lastName;

    private int age;

    private String picture;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TeamPlayer> teamPlayers;

    public User(String name, String lastName, int age, String picture) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.picture = picture;
        teamPlayers = new ArrayList<>();
    }

    public void addPlayer(Player player){
        TeamPlayer teamPlayer = new TeamPlayer(player);
        teamPlayers.add(teamPlayer);
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public List<TeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }

    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
        public String getPicture() {
        return picture;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", picture='" + picture + '\'' +
                ", teamPlayers=" + teamPlayers +
                '}';
    }
}

