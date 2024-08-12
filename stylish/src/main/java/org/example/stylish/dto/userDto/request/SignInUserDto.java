package org.example.stylish.dto.userDto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInUserDto {
    private String provider;
    private String email;
    private String password;
    @JsonAlias({"access_token"})
    private String accessToken;
}
