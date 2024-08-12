package org.example.stylish.dto.campaignDto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class UpdateCampaignDto {
    @JsonAlias("product_id")
    private BigInteger productId;
    private String story;
}
