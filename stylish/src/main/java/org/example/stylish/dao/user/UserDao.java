package org.example.stylish.dao.user;

import org.example.stylish.dto.userDto.request.SignUpUserDto;

import java.util.Map;

public interface UserDao {
    Map<String, Object> getUserByEmail(String email);

    Long insertUser(String provider, SignUpUserDto signUpUserDto, String pictureUrl);

}
