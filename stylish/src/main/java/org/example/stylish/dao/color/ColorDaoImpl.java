package org.example.stylish.dao.color;

import org.example.stylish.dto.ColorDto;
import org.example.stylish.model.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class ColorDaoImpl implements ColorDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Color getColor(String colorCode) {
        try {
            String sql = "SELECT * FROM color WHERE code = ?";
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Color(
                    rs.getLong("id"),
                    rs.getString("code"),
                    rs.getString("name")
            ), colorCode);
        } catch (EmptyResultDataAccessException erd) {
            return null;
        }
    }

    public Integer insertColor(ColorDto colorDto) {
        try {
            String sql = "INSERT INTO color(`name`, `code`) VALUES (?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, colorDto.getName());
                ps.setString(2, colorDto.getCode());
                return ps;
            }, keyHolder);

            return keyHolder.getKey().intValue();
        } catch (DataAccessException dae) {
            return 0;
        }
    }
}
