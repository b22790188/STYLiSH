package org.example.stylish.dto.userDto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserInfoDto {
    UserInfoAndTokenDto data;
}