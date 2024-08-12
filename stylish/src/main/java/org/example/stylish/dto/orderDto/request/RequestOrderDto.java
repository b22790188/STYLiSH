package org.example.stylish.dto.orderDto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDto {
    String shipping;
    String payment;
    Integer subtotal;
    Integer freight;
    Integer total;
    RequestRecipientDto recipient;
    List<RequestOrderProductDto> list;
}
