package org.example.stylish.dto.campaignDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"product_id", "story", "picture"})
public class ResponseCampaignDto {
    @JsonProperty("product_id")
    private BigInteger productId;
    private String story;
    private String picture;
}
