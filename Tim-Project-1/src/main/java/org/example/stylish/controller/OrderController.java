package org.example.stylish.controller;


import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.ErrorResponseDto;
import org.example.stylish.dto.ResponseDto;
import org.example.stylish.dto.orderDto.request.RequestCheckoutDto;
import org.example.stylish.dto.orderDto.response.ResponseOrderDto;
import org.example.stylish.dto.userDto.response.UserInfoDto;
import org.example.stylish.exception.VariantNoExistException;
import org.example.stylish.service.OrderService;
import org.example.stylish.userDetail.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Log4j2
@RestController
@RequestMapping("/api/1.0/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody RequestCheckoutDto requestCheckoutDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserInfoDto userInfoDtoDto = userDetails.getUserInfoDto();

        try {
            BigInteger orderNumber = orderService.checkout(requestCheckoutDto, userInfoDtoDto.getId());
            ResponseOrderDto responseOrderDto = ResponseOrderDto.builder().number(orderNumber.toString()).build();
            ResponseDto<ResponseOrderDto> responseDto = new ResponseDto<>(responseOrderDto);
            return !orderNumber.equals(BigInteger.valueOf(0)) ? new ResponseEntity<>(responseDto, HttpStatus.OK)
                : new ResponseEntity<>(new ErrorResponseDto("Payment failed"), HttpStatus.BAD_REQUEST);
        } catch (VariantNoExistException e) {
            return new ResponseEntity<>(new ErrorResponseDto("Product not exist!"), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorResponseDto("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleMalformedRequest(HttpMessageNotReadableException e) {
        return new ErrorResponseDto("Malformed json format");
    }
}

