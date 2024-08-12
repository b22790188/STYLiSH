package org.example.stylish.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.example.stylish.dao.user.UserDao;
import org.example.stylish.dto.userDto.request.SignInUserDto;
import org.example.stylish.dto.userDto.request.SignUpUserDto;
import org.example.stylish.dto.userDto.response.ResponseUserInfoDto;
import org.example.stylish.dto.userDto.response.UserInfoAndTokenDto;
import org.example.stylish.dto.userDto.response.UserInfoDto;
import org.example.stylish.exception.EmailAlreadyRegisteredException;
import org.example.stylish.exception.SignInFailedException;
import org.example.stylish.exception.UserNotExistException;
import org.example.stylish.utils.FacebookApiTemplate;
import org.example.stylish.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;

@Log4j2
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private FacebookApiTemplate facebookApiTemplate;

    public ResponseUserInfoDto signUp(SignUpUserDto signUpUserDto) {
        Map<String, Object> user = userDao.getUserByEmail(signUpUserDto.getEmail());
        if (user.isEmpty()) {
            String hashedPassword = passwordEncoder.encode(signUpUserDto.getPassword());
            signUpUserDto.setPassword(hashedPassword);

            userDao.insertUser("native", signUpUserDto, "image");
            Map<String, Object> newUser = userDao.getUserByEmail(signUpUserDto.getEmail());

            return getResponseUserInfoDtoAndSetJWt(newUser);
        } else {
            throw new EmailAlreadyRegisteredException("Email has been registered");
        }
    }

    public ResponseUserInfoDto nativeSignIn(SignInUserDto signInUserDto) {
        Map<String, Object> user = userDao.getUserByEmail(signInUserDto.getEmail());
        if (!user.isEmpty()) {
            boolean matchResult = passwordEncoder.matches(signInUserDto.getPassword(), (String) user.get("password"));
            if (matchResult) {
                return getResponseUserInfoDtoAndSetJWt(user);
            } else {
                throw new SignInFailedException("Login failed, please try again");
            }
        } else {
            throw new UserNotExistException("User not found, please signup first");
        }
    }

    public ResponseUserInfoDto thirdPartySignIn(SignInUserDto signInUserDto) {
        //using accessToken to call fb api first, to check if user is valid;
        JsonNode FBUserProfile = facebookApiTemplate.getUserInfo(signInUserDto.getAccessToken());
        String email = FBUserProfile.get("email").asText();
        log.info(email);
        Map<String, Object> user = userDao.getUserByEmail(email);

        if (!user.isEmpty()) {
            return getResponseUserInfoDtoAndSetJWt(user);
        } else {
            String name = FBUserProfile.get("name").asText();
            String hashedPassword = passwordEncoder.encode(email);

            String pictureUrl = FBUserProfile.get("picture").get("data").get("url").asText();

            SignUpUserDto signUpUserDto = new SignUpUserDto(name, email, hashedPassword);
            userDao.insertUser(signInUserDto.getProvider(), signUpUserDto, pictureUrl);
            Map<String, Object> newUser = userDao.getUserByEmail(signUpUserDto.getEmail());
            return getResponseUserInfoDtoAndSetJWt(newUser);
        }
    }

    //extracted duplicated code from previous method
    private ResponseUserInfoDto getResponseUserInfoDtoAndSetJWt(Map<String, Object> user) {
        UserInfoDto userInfoDto = getUserInfoDto(user);
        String jwt = JwtUtil.generateToken(userInfoDto);
        UserInfoAndTokenDto userInfoAndTokenDto = new UserInfoAndTokenDto(jwt, JwtUtil.EXPIRATION_TIME / 1000, userInfoDto);
        return new ResponseUserInfoDto(userInfoAndTokenDto);
    }


    private UserInfoDto getUserInfoDto(Map<String, Object> user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId((BigInteger) user.get("id"));
        userInfoDto.setProvider((String) user.get("provider"));
        userInfoDto.setName((String) user.get("name"));
        userInfoDto.setEmail((String) user.get("email"));
        userInfoDto.setPicture((String) user.get("picture"));
        return userInfoDto;
    }
}
