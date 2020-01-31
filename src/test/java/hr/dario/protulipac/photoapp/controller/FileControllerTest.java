package hr.dario.protulipac.photoapp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.Filer;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class FileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void addValidPicture() throws Exception{

        File testPictureFile = new File(getClass().getResource("/static/pict/pict001.jpg").getFile());
        System.out.println("Test picture: " + testPictureFile.getAbsolutePath());
        MockMultipartFile testPicture = new MockMultipartFile("file",
                "pict011.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                new FileInputStream(testPictureFile));

        this.mockMvc
                .perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(testPicture)
                        .param("name", "Test picuture")
                        .param("path", "/pict/pict001.png")
                        .param("description", "Just test picture")
                        .with(csrf())
                        .with(user("admin").password("admin").roles("USER", "ADMIN"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

    }
}
