package org.example.stylish.dao.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Repository
public class ImageDaoImpl implements ImageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String insertImageUrl(long productId, String imageUrl) {
        try {
            String sql = "INSERT INTO image (product_id, image_url) VALUES (?, ?)";
            jdbcTemplate.update(sql, productId, imageUrl);
            return "Insert image successfully";
        } catch (DataAccessException dae) {
            return dae.getMessage();
        }
    }

    public List<String> getImagesByProductId(long productId) {
        try {
            String sql = "SELECT i.image_url FROM product p JOIN image i ON p.id = i.product_id WHERE p.id=?";
            List<Map<String, Object>> imageRows = jdbcTemplate.queryForList(sql, productId);
            List<String> images = new ArrayList<>();
            for (Map<String, Object> row : imageRows) {
                images.add((String) row.get("image_url"));
            }
            return images;
        } catch (DataAccessException dae) {
            System.err.println("Error retrieving image list");
            return Collections.emptyList();
        }
    }
}
