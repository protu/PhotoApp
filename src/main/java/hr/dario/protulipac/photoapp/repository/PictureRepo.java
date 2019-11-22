package hr.dario.protulipac.photoapp.repository;

import hr.dario.protulipac.photoapp.domain.Picture;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PictureRepo {

    private final JdbcTemplate jdbc;

    public PictureRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Iterable<Picture> findAll() {
        return jdbc.query("select id, name, path, description, username from pictures", this::mapRowToPictures);
    }

    private Picture mapRowToPictures(ResultSet  resultSet, int rowNum) throws SQLException {
        Picture picture = new Picture();

        picture.setId(resultSet.getLong("id"));
        picture.setName(resultSet.getNString("name"));
        picture.setPath(resultSet.getNString("path"));
        picture.setDescription(resultSet.getNString("description"));
        picture.setUsername(resultSet.getNString("username"));

        return picture;
    }
}
