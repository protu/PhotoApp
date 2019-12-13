package hr.dario.protulipac.photoapp.repository;

import hr.dario.protulipac.photoapp.domain.Picture;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PictureRepo {

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert pictureInsertr;

    public PictureRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.pictureInsertr = new SimpleJdbcInsert(jdbc).withTableName("pictures").usingGeneratedKeyColumns("id");
    }

    public Iterable<Picture> findAll() {
        return jdbc.query("select id, name, path, description, username from pictures", this::mapRowToPictures);
    }

    private long savePictureDetails(Picture picture) {
        Map<String, Object> values = new HashMap<>();
        values.put("name", picture.getName());
        values.put("description", picture.getDescription());
        values.put("path", picture.getPath());
        values.put("username", picture.getUsername());

        return pictureInsertr.executeAndReturnKey(values).longValue();
    }

    public Picture save(Picture picture) {
        picture.setId(savePictureDetails(picture));
        return picture;
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
