package org.example.stylish.dto.ReportDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReportDto {
    @JsonProperty("user_id")
    private BigInteger userId;
    @JsonProperty("total_payment")
    private Integer totalPayment;
}
