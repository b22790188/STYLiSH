package org.example.stylish.mapper;

import org.example.stylish.dto.ColorDto;
import org.example.stylish.dto.VariantDto;
import org.example.stylish.dto.productDto.ProductInfoDto;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class ProductMapper {
    public static ProductInfoDto mapToProductInfoDto(Map<String, Object> product, List<ColorDto> colors, List<String> sizes, List<VariantDto> variants, List<String> images) {
        ProductInfoDto productInfoDto = new ProductInfoDto();
        productInfoDto.setId(((BigInteger) product.get("id")).longValue());
        productInfoDto.setCategory((String) product.get("category"));
        productInfoDto.setTitle((String) product.get("title"));
        productInfoDto.setDescription((String) product.get("description"));
        productInfoDto.setPrice((int) product.get("price"));
        productInfoDto.setTexture((String) product.get("texture"));
        productInfoDto.setWash((String) product.get("wash"));
        productInfoDto.setPlace((String) product.get("place"));
        productInfoDto.setNote((String) product.get("note"));
        productInfoDto.setStory((String) product.get("story"));
        productInfoDto.setColors(colors);
        productInfoDto.setSizes(sizes);
        productInfoDto.setVariants(variants);
        productInfoDto.setMain_image((String) product.get("main_image"));
        productInfoDto.setImages(images);

        return productInfoDto;
    }
}
