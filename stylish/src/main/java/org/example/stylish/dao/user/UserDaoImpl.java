package org.example.stylish.dao.user;

import org.example.stylish.dto.userDto.request.SignUpUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM user WHERE email = ?";
            return jdbcTemplate.queryForMap(sql, email);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyMap();
        } catch (DataAccessException dae) {
            throw dae;
        }
    }

    public Long insertUser(String provider, SignUpUserDto signUpUserDto, String pictureUrl) {
        try {
            String sql = "INSERT INTO user (`provider`, `name`, `email`, `password`, `picture`) VALUES (?,?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, provider);
                ps.setString(2, signUpUserDto.getName());
                ps.setString(3, signUpUserDto.getEmail());
                ps.setString(4, signUpUserDto.getPassword());
                ps.setString(5, pictureUrl);
                return ps;
            }, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (DataAccessException dae) {
            System.err.println("Error inserting user");
            throw dae;
        }
    }
}
