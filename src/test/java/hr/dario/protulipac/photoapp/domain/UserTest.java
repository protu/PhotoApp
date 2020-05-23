package hr.dario.protulipac.photoapp.domain;

import hr.dario.protulipac.photoapp.builder.UserRoleBuilder;
import hr.dario.protulipac.photoapp.repository.UserRepo;
import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserTest {


    @BeforeAll
    public static void createUser(@Autowired UserRepo userRepo) {
        UserRoleBuilder userRoleBuilder = new UserRoleBuilder();
        userRoleBuilder.setEnabled(true);
        userRoleBuilder.setUsername("testUser");
        userRoleBuilder.setPassword("b00req10.5");
        userRepo.save(userRoleBuilder.getBuilder());
    }

    @Test
    void getUser(@Autowired UserRepo userRepo) {
        User user = userRepo.findById("testUser").get();
        Assertions.assertEquals("testUser", user.getUsername());
    }

    @AfterAll
    static void deleteUser(@Autowired UserRepo userRepo) {
        userRepo.deleteById("testUser");
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            userRepo.findById("testUser").get();
        });
    }
}
