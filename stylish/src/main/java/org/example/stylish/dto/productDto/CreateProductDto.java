package org.example.stylish.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.stylish.dto.ColorDto;
import org.example.stylish.dto.VariantDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    String category;
    String title;
    String description;
    Integer price;
    String texture;
    String wash;
    String place;
    String note;
    String story;
    ColorDto[] colors;
    String[] sizes;
    VariantDto[] variants;
}
