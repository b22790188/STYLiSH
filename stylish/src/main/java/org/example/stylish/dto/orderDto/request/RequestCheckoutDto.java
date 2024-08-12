package org.example.stylish.dto.orderDto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCheckoutDto {
    String prime;
    RequestOrderDto order;
}
