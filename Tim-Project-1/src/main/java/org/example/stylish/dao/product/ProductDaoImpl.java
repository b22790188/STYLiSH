package org.example.stylish.dao.product;

import org.example.stylish.dto.productDto.CreateProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final Integer LIMIT = 7;

    public Integer insertProduct(CreateProductDto requestProductDTO, String mainImageUrl) {
        try {
            String sql = "INSERT INTO product (`category`, `title`, `description`, `price`, `texture`, `wash`, `place`, `note`, `story`, `main_image`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, requestProductDTO.getCategory());
                ps.setString(2, requestProductDTO.getTitle());
                ps.setString(3, requestProductDTO.getDescription());
                ps.setInt(4, requestProductDTO.getPrice());
                ps.setString(5, requestProductDTO.getTexture());
                ps.setString(6, requestProductDTO.getWash());
                ps.setString(7, requestProductDTO.getPlace());
                ps.setString(8, requestProductDTO.getNote());
                ps.setString(9, requestProductDTO.getStory());
                ps.setString(10, mainImageUrl);
                return ps;
            }, keyHolder);

            return keyHolder.getKey().intValue();
        } catch (NullPointerException npe) {
            return 0;
        }
    }

    public Map<String, Object> getProductById(long productId) {
        try {
            String sql = "SELECT * FROM product WHERE id = ?";
            return jdbcTemplate.queryForMap(sql, productId);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyMap();
        } catch (DataAccessException dae) {
            System.err.println("Error access to data");
            throw dae;
        }
    }

    public List<Map<String, Object>> getAllProducts() {
        try {
            String sql = "SELECT * FROM product";
            return jdbcTemplate.queryForList(sql);
        } catch (DataAccessException dae) {
            return Collections.emptyList();
        }
    }

    public List<Map<String, Object>> getAllProducts(Integer paging) {
        try {
            String sql = "SELECT * FROM product LIMIT ? OFFSET ?";
            return jdbcTemplate.queryForList(sql, LIMIT, paging * 6);

        } catch (DataAccessException dae) {
            System.err.println("Error retrieving product with ID");
            return Collections.emptyList();
        }
    }

    public List<Map<String, Object>> getProductsByCategory(Integer paging, String category) {
        try {
            String sql = "SELECT * FROM product WHERE category = ? LIMIT ? OFFSET ?";
            return jdbcTemplate.queryForList(sql, category, LIMIT, paging * 6);
        } catch (DataAccessException dae) {
            System.err.println("Error retrieving product with category");
            return Collections.emptyList();
        }
    }

    public List<Map<String, Object>> getProductsBySearch(Integer paging, String keyword) {
        try {
            String sql = "SELECT * FROM product WHERE title LIKE ? LIMIT ? OFFSET ?";
            return jdbcTemplate.queryForList(sql, "%" + keyword + "%", LIMIT, paging * 6);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Error retrieving product with keyword");
            return Collections.emptyList();
        } catch (DataAccessException dae) {
            System.err.println("Error access to data");
            throw dae;
        }
    }
}
