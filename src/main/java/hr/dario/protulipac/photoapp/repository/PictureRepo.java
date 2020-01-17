package hr.dario.protulipac.photoapp.repository;

import hr.dario.protulipac.photoapp.domain.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PictureRepo extends CrudRepository<Picture, Long> {

    Iterable<Picture> findAllByUsername(String username);

    Optional<Picture> findByIdAndUsername(Long id, String username);

    Boolean existsByIdAndUsername(Long id, String username);
}
