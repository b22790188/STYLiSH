package org.example.stylish.dao.junction;

import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.ColorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository
public class JunctionDaoImpl implements JunctionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String insertProductIdAndSizeId(Long productId, Long sizeId) {
        try {
            String sql = "INSERT INTO product_size(product_id,size_id) values(?,?)";
            jdbcTemplate.update(sql, productId, sizeId);
            return "Insert product_size successfully!";
        } catch (DataAccessException dae) {
            return dae.getMessage();
        }
    }

    public String insertProductIdAndColorId(Long productId, Long colorId) {
        try {
            String sql = "INSERT INTO product_color(product_id,color_id) values(?,?)";
            jdbcTemplate.update(sql, productId, colorId);
            return "Insert product_color successfully!";
        } catch (DataAccessException dae) {
            return dae.getMessage();
        }
    }

    public boolean insertOrderVariant(BigInteger orderId, BigInteger variantId, int quantity) {
        try {
            String sql = "INSERT INTO order_variant(`order_id`,`variant_id`,`qty`) values(?,?,?)";
            jdbcTemplate.update(sql, orderId, variantId, quantity);
            return true;
        } catch (DataAccessException dae) {
            log.error("Error inserting order_variant table");
            return false;
        }
    }

    public List<ColorDto> getColorsByProductId(long productId) {
        try {
            String sql = "SELECT c.name, c.code FROM product p JOIN product_color pc ON p.id = pc.product_id JOIN color c ON c.id = pc.color_id WHERE p.id = ?";
            List<Map<String, Object>> colorRows = jdbcTemplate.queryForList(sql, productId);
            List<ColorDto> colorDtos = new ArrayList<>();
            for (Map<String, Object> row : colorRows) {
                ColorDto colorDto = new ColorDto();
                colorDto.setName((String) row.get("name"));
                colorDto.setCode((String) row.get("code"));
                colorDtos.add(colorDto);
            }
            return colorDtos;
        } catch (DataAccessException dae) {
            System.err.println("Error retrieving colors");
            return Collections.emptyList();
        }
    }

    public List<String> getSizesByProductId(long productId) {
        try {
            String sql = "SELECT s.size FROM product p JOIN product_size ps ON p.id = ps.product_id JOIN size s ON s.id = ps.size_id WHERE p.id = ?";
            List<Map<String, Object>> sizeRows = jdbcTemplate.queryForList(sql, productId);
            List<String> sizes = new ArrayList<>();
            for (Map<String, Object> row : sizeRows) {
                sizes.add((String) row.get("size"));
            }
            return sizes;
        } catch (DataAccessException dae) {
            System.err.println("Error retrieving product sizes");
            return Collections.emptyList();
        }
    }
}
