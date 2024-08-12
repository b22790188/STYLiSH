package org.example.stylish.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.ErrorResponseDto;
import org.example.stylish.dto.userDto.request.SignInUserDto;
import org.example.stylish.dto.userDto.request.SignUpUserDto;
import org.example.stylish.dto.userDto.response.ResponseUserInfoDto;
import org.example.stylish.dto.userDto.response.ResponseUserProfileDto;
import org.example.stylish.dto.userDto.response.UserInfoDto;
import org.example.stylish.dto.userDto.response.UserProfileDto;
import org.example.stylish.exception.EmailAlreadyRegisteredException;
import org.example.stylish.exception.SignInFailedException;
import org.example.stylish.exception.UserNotExistException;
import org.example.stylish.exception.WrongFBTokenException;
import org.example.stylish.service.UserService;
import org.example.stylish.userDetail.CustomUserDetails;
import org.example.stylish.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/user")
@Log4j2
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpUserDto signUpUserDto, HttpServletResponse response) {
        try {
            ResponseUserInfoDto responseUserInfoDto = userService.signUp(signUpUserDto);
            return getResponseEntity(response, responseUserInfoDto);

        } catch (EmailAlreadyRegisteredException e) {
            return new ResponseEntity<>(new ErrorResponseDto(e.getMessage()), HttpStatus.FORBIDDEN);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new ErrorResponseDto("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInUserDto signInUserDto, HttpServletResponse response) {
        ResponseUserInfoDto responseUserInfoDto;
        try {
            if (signInUserDto.getAccessToken() != null) {
                responseUserInfoDto = userService.thirdPartySignIn(signInUserDto);
            } else {
                responseUserInfoDto = userService.nativeSignIn(signInUserDto);
            }
            return getResponseEntity(response, responseUserInfoDto);

        } catch (SignInFailedException | WrongFBTokenException e) {
            return new ResponseEntity<>(new ErrorResponseDto(e.getMessage()), HttpStatus.FORBIDDEN);
        } catch (UserNotExistException e) {
            return new ResponseEntity<>(new ErrorResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDto("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            UserInfoDto userInfoDto = userDetails.getUserInfoDto();
            UserProfileDto userProfileDto = new UserProfileDto(
                userInfoDto.getProvider(),
                userInfoDto.getName(),
                userInfoDto.getEmail(),
                userInfoDto.getPicture()
            );
            ResponseUserProfileDto responseUserProfileDto = new ResponseUserProfileDto(userProfileDto);
            return new ResponseEntity<>(responseUserProfileDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDto("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errorResponseDto.setError(errorMessage);
        });
        return errorResponseDto;
    }

    //extracted duplicated code from previous method
    private ResponseEntity<?> getResponseEntity(HttpServletResponse response, ResponseUserInfoDto responseUserInfoDto) {
        Cookie cookie = new Cookie("jwt", responseUserInfoDto.getData().getAccessToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(JwtUtil.EXPIRATION_TIME / 1000);

        response.addCookie(cookie);


        return new ResponseEntity<>(responseUserInfoDto, HttpStatus.OK);
    }
}
