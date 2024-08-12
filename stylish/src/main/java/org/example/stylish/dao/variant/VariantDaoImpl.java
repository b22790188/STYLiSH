package org.example.stylish.dao.variant;

import org.example.stylish.dto.VariantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class VariantDaoImpl implements VariantDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Integer insertVariant(long productId, long colorId, long sizeId, int stock) {
        try {
            String sql = "INSERT INTO variant (product_id, color_id, size_id, stock) VALUES (?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, productId);
                ps.setLong(2, colorId);
                ps.setLong(3, sizeId);
                ps.setInt(4, stock);
                return ps;
            }, keyHolder);

            return keyHolder.getKey().intValue();
        } catch (DataAccessException dae) {
            return 0;
        }
    }

    public Optional<Map<String, Object>> getVariantByProductIdColorIdSizeId(BigInteger productId, BigInteger colorId, BigInteger sizeId) {
        try {
            String sql = "SELECT id, stock FROM variant WHERE product_id = ? and color_id = ? and size_id = ?";
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, productId, colorId, sizeId);
            return Optional.of(result);
        } catch (EmptyResultDataAccessException erdae) {
            return Optional.empty();
        } catch (DataAccessException dae) {
            throw dae;
        }
    }

    public List<VariantDto> getVariantsByProductId(long productId) {
        try {
            String sql = "SELECT c.code, s.size, v.stock FROM product p JOIN variant v ON p.id = v.product_id JOIN color c ON c.id = v.color_id JOIN size s ON s.id = v.size_id WHERE p.id = ?";
            List<Map<String, Object>> variantRows = jdbcTemplate.queryForList(sql, productId);
            List<VariantDto> variantDtos = new ArrayList<>();
            for (Map<String, Object> row : variantRows) {
                VariantDto variantDto = new VariantDto();
                variantDto.setColorCode((String) row.get("code"));
                variantDto.setSize((String) row.get("size"));
                variantDto.setStock((Integer) row.get("stock"));
                variantDtos.add(variantDto);
            }
            return variantDtos;
        } catch (DataAccessException dae) {
            System.err.println("Error retrieving variants");
            return Collections.emptyList();
        }
    }
}
