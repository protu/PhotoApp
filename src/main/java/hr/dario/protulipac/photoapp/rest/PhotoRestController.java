package hr.dario.protulipac.photoapp.rest;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import hr.dario.protulipac.photoapp.security.SecurityUtils;
import hr.dario.protulipac.photoapp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/photo", produces = "application/json")
@Secured("ROLE_USER")
public class PhotoRestController {

    private final PictureRepo pictureRepo;
    private final FileService fileService;
//    private String username = SecurityUtils.getUsername();
    private String username = "admin";

    @Autowired
    public PhotoRestController(FileService fileService, PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
        this.fileService = fileService;
    }

    @GetMapping
    public Iterable<Picture> findAll() {
        return SecurityUtils.isAdmin() ? pictureRepo.findAll() : pictureRepo.findAllByUsername(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Picture> findPict(@PathVariable Long id) {
        Optional<Picture> pictureOptional = pictureRepo.findById(id);
        return pictureOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws HttpClientErrorException.NotFound {
        boolean exists = SecurityUtils.isAdmin() ? pictureRepo.existsById(id) : pictureRepo.existsByIdAndUsername(id, username);
        if (exists) {
            pictureRepo.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such ID");
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Picture> update(@PathVariable Long id, @Valid @RequestBody Picture updtPicture) {
        Optional<Picture> picture = SecurityUtils.isAdmin() ? pictureRepo.findById(id) : pictureRepo.findByIdAndUsername(id, username);

        picture.ifPresent(value -> {
            value.setName(updtPicture.getName());
            value.setDescription(updtPicture.getDescription());
            value.setPath(updtPicture.getPath());
            value.setUsername(username);

            pictureRepo.save(value);
        });

        return picture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Picture addNeWWithDesc(@RequestParam(value = "file") MultipartFile file, @Valid @RequestPart(value = "picture") Picture picture) throws IOException {
        picture.setUsername(username);
        picture.setPath("pict" + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
        fileService.uploadFile(file);
        return pictureRepo.save(picture);
    }

}
