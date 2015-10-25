package demo.service;

import demo.domain.matchdaypoints.MatchDayPointRepository;
import demo.domain.matchdaypoints.MatchDayPoints;
import demo.domain.players.Player;
import demo.domain.players.PlayerRepository;
import demo.domain.players.TeamPlayer;
import demo.domain.players.TeamPlayerRepository;
import demo.infrastructure.helpers.FileReaderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by glenn on 9/23/2015.
 */
@Service
public class FileProcessingServiceImpl implements FileProcessingService {
    private final PlayerRepository playerRepository;
    private final TeamPlayerRepository teamPlayerRepository;
    private final MatchDayPointRepository matchDayPointRepository;

    @Autowired
    public FileProcessingServiceImpl(PlayerRepository playerRepository,TeamPlayerRepository teamPlayerRepository,MatchDayPointRepository matchDayPointRepository) {
        this.playerRepository = playerRepository;
        this.teamPlayerRepository = teamPlayerRepository;
        this.matchDayPointRepository = matchDayPointRepository;
    }

    public void calculatePointsPlayers(){
        String fileName = "C:/LEAGUE/MATCHDAYPOINTS.txt";
        try {
            List<String> lines = FileReaderHelper.readFile(fileName);
            lines.stream().filter(x->x.split(",").length == 3).forEach(x -> {
                String[] values = x.split(",");
                int day = Integer.parseInt(values[0]);
                String name = values[1];
                int points = Integer.parseInt(values[2]);
                Player p = playerRepository.findOneByName(name);
                if(p == null)
                    return;
                p.addMathDayPoints(new MatchDayPoints(day,points));
                playerRepository.save(p);

                TeamPlayer teamPlayer = teamPlayerRepository.findOneByName(name);
                if(teamPlayer == null)
                    return;
                MatchDayPoints pointsMatchDay = teamPlayer.getPlayer().getPointsByDay(day);
                if(pointsMatchDay == null)
                    return;
                teamPlayer.addActivePoints(pointsMatchDay);
                teamPlayerRepository.save(teamPlayer);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calculateActiveMatchDays(){
        String fileName = "C:/LEAGUE/ACTIVE.txt";
        try {
            List<String> lines = FileReaderHelper.readFile(fileName);
            lines.stream().filter(x->x.split(",").length == 3).forEach(x ->{
                String[] values = x.split(",");
                int day = Integer.parseInt(values[0]);
                //String user = values[1];
                String player = values[1];

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
