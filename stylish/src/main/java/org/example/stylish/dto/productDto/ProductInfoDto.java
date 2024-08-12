package org.example.stylish.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.stylish.dto.ColorDto;
import org.example.stylish.dto.VariantDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDto {
    private long id;
    String category;
    String title;
    String description;
    Integer price;
    String texture;
    String wash;
    String place;
    String note;
    String story;
    List<ColorDto> colors;
    List<String> sizes;
    List<VariantDto> variants;
    private String main_image;
    private List<String> images;
}
