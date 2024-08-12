package org.example.stylish.dto.tapPayDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTapPayDto {
    String prime;
    @JsonProperty("partner_key")
    String partnerKey;
    @JsonProperty("merchant_id")
    String merchantId;
    @JsonProperty("order_number")
    String orderNumber;
    String details;
    Integer amount;
    CardHolderDto cardholder;
}
