package hr.dario.protulipac.photoapp.controller;

import hr.dario.protulipac.photoapp.repository.PictureRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class EditControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void editor() throws Exception {
        this.mockMvc.perform(post("/edit")
                .param("id", "1")
                .with(csrf())
                .with(user("admin").password("admin").roles("USER", "ADMIN"))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    public void editPicture() throws Exception {
        this.mockMvc.perform(post("/edit")
                .param("name", "Train")
                .param("description", "Train is coming")
                .with(csrf())
                .param("id", "1")
                .param("change", "1")
                .with(user("admin").password("admin").roles("USER", "ADMIN"))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }


}