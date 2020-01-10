package hr.dario.protulipac.photoapp.controller;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.repository.PictureRepo;
import hr.dario.protulipac.photoapp.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller

public class FileController {

    private Logger log = LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;
    private final PictureRepo pictureRepo;
    @Autowired
    public FileController(FileService fileService, PictureRepo pictureRepo) {
        this.fileService = fileService;
        this.pictureRepo = pictureRepo;
    }

    @GetMapping("/upload")
    public String index(Model model) {
        model.addAttribute("picture", new Picture());
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Picture picture, Model model) throws IOException {
        String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        fileService.uploadFile(file);
        model.addAttribute("message", "You have successfully uploaded " + picture.getName() + " picture!");
        picture.setPath("pict/" + file.getOriginalFilename());
        picture.setUsername(username);
        pictureRepo.save(picture);
        log.info("You have successfully uploaded " + picture.getName() + " picture!");
        List<Picture> pictures = new ArrayList<>();
        pictureRepo.findAll().forEach(pictures::add);
        model.addAttribute("pictures", pictures);
        return "home";
    }
}



