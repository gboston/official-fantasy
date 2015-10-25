package demo.domain.players;

import demo.domain.matchdaypoints.MatchDayPoints;

import javax.persistence.*;
import java.util.List;

@Entity
public class TeamPlayer{
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Player player;

    @OneToMany(fetch = FetchType.EAGER)
    private List<MatchDayPoints> activeMatchDayPoints;

    public boolean onField;

    public TeamPlayer() {
    }

    public void addActivePoints(MatchDayPoints points){
        activeMatchDayPoints.add(points);
    }

    public List<MatchDayPoints> getActiveMatchDayPoints() {
        return activeMatchDayPoints;
    }

    public void setActiveMatchDayPoints(List<MatchDayPoints> activeMatchDayPoints) {
        this.activeMatchDayPoints = activeMatchDayPoints;
    }

    public TeamPlayer(Player player) {
        this.player = player;
    }

    public boolean isOnField() {
        return onField;
    }

    public void setOnField(boolean onField) {
        this.onField = onField;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "TeamPlayer{" +
                "id=" + id +
                ", player=" + player +
                ", onField=" + onField +
                '}';
    }
}
