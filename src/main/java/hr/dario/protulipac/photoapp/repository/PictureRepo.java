package hr.dario.protulipac.photoapp.repository;

import hr.dario.protulipac.photoapp.domain.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public interface PictureRepo extends CrudRepository<Picture, Long> {
}
