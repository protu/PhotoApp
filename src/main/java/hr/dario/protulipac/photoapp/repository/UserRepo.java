package hr.dario.protulipac.photoapp.repository;

import hr.dario.protulipac.photoapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
