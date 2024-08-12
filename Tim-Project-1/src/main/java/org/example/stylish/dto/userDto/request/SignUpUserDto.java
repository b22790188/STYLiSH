package org.example.stylish.dto.userDto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserDto {
    @NotBlank(message = "This column cannot be blank")
    String name;
    @NotBlank(message = "This column cannot be blank")
    @Email(message = "Please provide a valid email address")
    String email;
    @NotBlank(message = "This column cannot be blank")
    String password;
}
