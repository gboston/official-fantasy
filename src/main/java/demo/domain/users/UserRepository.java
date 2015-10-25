package demo.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by glenn on 9/22/2015.
 */
@RepositoryRestResource
public interface UserRepository extends  JpaRepository<User,Long> {
    User findOneByName(String user);
}
