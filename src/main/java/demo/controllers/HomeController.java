package demo.controllers;

import demo.domain.players.Player;
import demo.domain.players.PlayerRepository;
import demo.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 10/3/2015.
 */

@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public HomeController(UserRepository userRepository,PlayerRepository playerRepository){

        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    @RequestMapping("/ranking")
    public String Ranking(Model model){
        List<UserModel> users = new ArrayList<>();
        userRepository.findAll().stream().forEach(x -> {
            int totalPoints = x.getTeamPlayers().stream().flatMap(p -> p.getActiveMatchDayPoints().stream()).mapToInt(playDay -> playDay.getPoints()).sum();
            users.add(new UserModel(x.getName(), totalPoints));
        });
        users.sort((a, b) -> {
            if (a.getPoints() < b.getPoints())
                return 1;
            if (a.getPoints() == b.getPoints())
                return 0;
            return -1;
        });
        model.addAttribute("users", users);
        model.addAttribute("lastUser",users.get(users.size()-1));
        model.addAttribute("firstUser", users.get(0));
        return "ranking";
    }

    @RequestMapping("/")
    public String Home(Model model){
        model.addAttribute("glenn", "glenn");
        List<UserModel> users = new ArrayList<>();
        userRepository.findAll().stream().forEach(x -> {
            int totalPoints = x.getTeamPlayers().stream().flatMap(p -> p.getActiveMatchDayPoints().stream()).mapToInt(playDay -> playDay.getPoints()).sum();
            users.add(new UserModel(x.getName(), totalPoints));
        });
        users.sort((a, b) -> {
            if (a.getPoints() < b.getPoints())
                return 1;
            if (a.getPoints() == b.getPoints())
                return 0;
            return -1;
        });
        model.addAttribute("users", users);

        List<PlayerModel> topPlayers = new ArrayList<>();
        playerRepository.findAll().stream().sorted((a,b)->{
            if(a.getPoints()<b.getPoints())
                return 1;
            if(a.getPoints() == b.getPoints())
                return 0;
            return -1;
        }).limit(10).forEach(x -> {
            topPlayers.add(new PlayerModel(x.getName(),x.getPoints()));
        });
        model.addAttribute("topPlayers",topPlayers);
        return "index";
    }
}
