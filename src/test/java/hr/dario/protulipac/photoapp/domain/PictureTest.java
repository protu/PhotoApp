package hr.dario.protulipac.photoapp.domain;

import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PictureTest {

    @Autowired
    PictureRepo pictureRepo;
    @Autowired
    DataSource dataSource;

    @Test
    void testInjectedComponents() {
        Assertions.assertNotNull(pictureRepo);
        Assertions.assertNotNull(dataSource);
    }

    @Test
    void getAllPictures() {
        Assertions.assertNotNull(pictureRepo.findAll());
    }

}
