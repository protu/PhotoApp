package hr.dario.protulipac.photoapp.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PhotoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void findAll() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/photo")
                                .with(user("admin").password("admin").roles("USER", "ADMIN"))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void findPict() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/photo")
                                .param("id", "1")
                                .with(user("admin").password("admin").roles("USER", "ADMIN"))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void addNewPicture() throws Exception {
        File testPictureFile = new File(getClass().getResource("/static/pict/pict001.jpg").getFile());
        System.out.println("Test picture: " + testPictureFile.getAbsolutePath());
        MockMultipartFile testPicture = new MockMultipartFile("file",
                "pict011.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                new FileInputStream(testPictureFile));

        Map<String, String> pictureMap = new HashMap<>();
        pictureMap.put("name", "Test picture");
        pictureMap.put("path", "/pict/pict011.jpg");
        pictureMap.put("description", "Test picture");
        pictureMap.put("username", "admin");

        MockMultipartFile pictureJson = new MockMultipartFile("picture", "", MediaType.APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsString(pictureMap).getBytes());

        System.out.println(objectMapper.writeValueAsString(pictureMap));
        RequestBuilder rb = MockMvcRequestBuilders.multipart("/api/photo/new")
                .file(testPicture)
                .file(pictureJson)
                .with(csrf())
                .with(user("admin").password("admin").roles("USER", "ADMIN"));


        this.mockMvc.perform(rb).andExpect(status().isCreated());
    }


    @Test
    public void deletePicture() throws Exception {
        this.mockMvc.perform(delete("/api/photo/8")
                .with(csrf())
                .with(user("admin").password("admin").roles("USER", "ADMIN"))
        ).andExpect(status().isNoContent());
    }

    @Test
    public void updatePicture() throws Exception {
        Map<String, String> pictureMap = new HashMap<>();
        pictureMap.put("name", "Test picture");
        pictureMap.put("description", "Test picture");
        pictureMap.put("username", "admin");
        pictureMap.put("path", "/pict/pict001.jpg");

        this.mockMvc.perform(put("/api/photo/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(pictureMap))
                .with(csrf())
                .with(user("admin").password("admin").roles("USER", "ADMIN")
                )).andExpect(status().isOk());
    }


}