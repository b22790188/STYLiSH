package org.example.stylish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"color_code", "size", "stock"})
public class VariantDto {
    @JsonProperty("color_code")
    String colorCode;
    String size;
    int stock;
}
