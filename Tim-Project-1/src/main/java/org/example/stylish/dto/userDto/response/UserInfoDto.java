package org.example.stylish.dto.userDto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    BigInteger id;
    String provider;
    String name;
    String email;
    String picture;
}
