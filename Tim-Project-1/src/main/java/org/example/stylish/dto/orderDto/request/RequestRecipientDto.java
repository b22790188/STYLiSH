package org.example.stylish.dto.orderDto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRecipientDto {
    String name;
    String phone;
    String email;
    String address;
    String time;
}

