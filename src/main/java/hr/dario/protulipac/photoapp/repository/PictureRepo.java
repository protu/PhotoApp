package hr.dario.protulipac.photoapp.repository;

import hr.dario.protulipac.photoapp.domain.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PictureRepo extends CrudRepository<Picture, Long> {
}
