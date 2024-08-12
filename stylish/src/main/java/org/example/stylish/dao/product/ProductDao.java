package org.example.stylish.dao.product;

import org.example.stylish.dto.productDto.CreateProductDto;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    Integer insertProduct(CreateProductDto requestProductDto, String mainImageUrl);

    Map<String, Object> getProductById(long productId);

    List<Map<String, Object>> getAllProducts();

    List<Map<String, Object>> getAllProducts(Integer paging);

    List<Map<String, Object>> getProductsByCategory(Integer paging, String category);

    List<Map<String, Object>> getProductsBySearch(Integer paging, String keyword);
}
