package demo.domain.players;

import demo.domain.users.User;
import demo.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TeamPlayerRepositoryImpl implements CustomMethodsTeamPlayers {
    private final UserRepository repo;

    @Autowired
    public TeamPlayerRepositoryImpl(UserRepository repo){

        this.repo = repo;
    }
    public TeamPlayer findOneByName(String player){
        List<User> u = repo.findAll();
        if(u == null)
            return null;
        return u.stream().flatMap(x -> x.getTeamPlayers().stream()).filter(x -> x.getPlayer().getName().contentEquals(player)).findFirst().get();
    }
}
