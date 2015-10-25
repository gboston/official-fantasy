package demo.domain.players;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TeamPlayerRepository  extends JpaRepository<TeamPlayer,Long>,CustomMethodsTeamPlayers {

}

