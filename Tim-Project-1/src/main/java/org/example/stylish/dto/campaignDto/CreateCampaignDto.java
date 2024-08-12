package org.example.stylish.dto.campaignDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCampaignDto {
    BigInteger productId;
    String story;
}
