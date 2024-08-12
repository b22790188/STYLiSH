package org.example.stylish.dao.recipient;

import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.orderDto.request.RequestRecipientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;

@Log4j2
@Repository
public class RecipientDaoImpl implements RecipientDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BigInteger insertRecipient(RequestRecipientDto requestRecipientDto) {
        try {
            String sql = "INSERT INTO recipient (`name`, `phone`, `email`, `address`, `time`) VALUES(?,?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, requestRecipientDto.getName());
                ps.setString(2, requestRecipientDto.getPhone());
                ps.setString(3, requestRecipientDto.getEmail());
                ps.setString(4, requestRecipientDto.getAddress());
                ps.setString(5, requestRecipientDto.getTime());
                return ps;
            }, keyHolder);
            return BigInteger.valueOf(keyHolder.getKey().longValue());
        } catch (NullPointerException npe) {
            log.error(npe.getMessage());
            throw npe;
        }
    }
}
