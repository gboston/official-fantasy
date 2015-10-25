package demo.domain.matchdaypoints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MatchDayPointRepository  extends JpaRepository<MatchDayPoints,Long> {

}
