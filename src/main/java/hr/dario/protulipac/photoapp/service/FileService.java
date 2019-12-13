package hr.dario.protulipac.photoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//marin je bio ovdje :D

@Service
public class FileService {

    @Autowired
    ResourceLoader resourceLoader;

    public String uploadDir;

    public void uploadFile(MultipartFile file) throws IOException {

        uploadDir = resourceLoader.getResource("classpath:/static/pict/").getFile().getPath();
        Path copyLocation = null;
        try {
            copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + copyLocation.toString() + " : " + file.getOriginalFilename() + ". Please try again!");
        }
    }
}
