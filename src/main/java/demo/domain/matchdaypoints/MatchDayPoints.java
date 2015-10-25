package demo.domain.matchdaypoints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by glenn on 9/22/2015.
 */
@Entity
public class MatchDayPoints {

    @GeneratedValue
    @Id
    private long id;

    private int day;
    private int points;

    public MatchDayPoints(int day, int points) {
        this.day = day;
        this.points = points;
    }

    public MatchDayPoints() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "MatchDayPoints{" +
                "day=" + day +
                ", points=" + points +
                '}';
    }
}
