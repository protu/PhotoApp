package hr.dario.protulipac.photoapp.rest;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import hr.dario.protulipac.photoapp.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
