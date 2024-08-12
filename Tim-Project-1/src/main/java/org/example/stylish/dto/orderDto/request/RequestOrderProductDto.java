package org.example.stylish.dto.orderDto.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigInteger;

@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderProductDto {
    String id;
    String name;
    Integer price;
    RequestOrderProductColorDto color;
    String size;
    Integer qty;

    @Nullable
    public BigInteger getIdAsBigInteger() {
        try {
            return new BigInteger(this.id);
        } catch (NumberFormatException e) {
            log.error("Invalid product ID");
            return null;
        }
    }
}
