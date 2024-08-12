package org.example.stylish.dao.size;

import org.example.stylish.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class SizeDaoImpl implements SizeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Size getSize(String size) {
        try {
            String sql = "SELECT * FROM size WHERE size = ?";
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Size(
                    rs.getLong("id"),
                    rs.getString("size")
            ), size);

        } catch (EmptyResultDataAccessException erd) {
            return null;
        }
    }

    public Integer insertSize(String size) {
        try {
            String sql = "INSERT INTO size(`size`) VALUES (?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, size);
                return ps;
            }, keyHolder);

            return keyHolder.getKey().intValue();
        } catch (NullPointerException npe) {
            return 0;
        }
    }
}
