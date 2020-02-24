package hr.dario.protulipac.photoapp.repository;

import hr.dario.protulipac.photoapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
}
