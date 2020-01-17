package hr.dario.protulipac.photoapp.rest;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import hr.dario.protulipac.photoapp.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/photo", produces = "application/json")
@Secured("ROLE_USER")
public class PhotoRestController {

    private final PictureRepo pictureRepo;

    @Autowired
    public PhotoRestController(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    @GetMapping
    public Iterable<Picture> findAll() {
        return SecurityUtils.isAdmin() ? pictureRepo.findAll() : pictureRepo.findAllByUsername(SecurityUtils.getUsername());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Picture> findPict(@PathVariable Long id) {
        String username = SecurityUtils.getUsername();
        Optional<Picture> pictureOptional = pictureRepo.findByIdAndUsername(id, username);
        return pictureOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        boolean exists = SecurityUtils.isAdmin() ? pictureRepo.existsById(id) : pictureRepo.existsByIdAndUsername(id, SecurityUtils.getUsername());
        if(exists){
            pictureRepo.deleteById(id);
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Picture> update(@PathVariable Long id, @Valid @RequestBody Picture updtPicture) {
        Optional<Picture> picture = SecurityUtils.isAdmin() ? pictureRepo.findById(id) : pictureRepo.findByIdAndUsername(id, SecurityUtils.getUsername());

        picture.ifPresent(value -> {
            value.setName(updtPicture.getName());
            value.setDescription(updtPicture.getDescription());
            value.setPath(updtPicture.getPath());

            pictureRepo.save(value);
        });

        return picture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
