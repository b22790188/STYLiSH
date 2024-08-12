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
public class CardHolderDto {
    @JsonProperty("phone_number")
    String phoneNumber;
    String name;
    String email;
}
