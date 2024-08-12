package org.example.stylish.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.tapPayDto.RequestTapPayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Log4j2
public class TapPayApiTemplate {
    @Autowired
    private ObjectMapper mapper;

    private static String TAPPAY_API_URL = "https://sandbox.tappaysdk.com/tpc/payment/pay-by-prime";

    public boolean getTapPayResponse(RequestTapPayDto requestTapPayDto) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-api-key", requestTapPayDto.getPartnerKey());

            HttpEntity<RequestTapPayDto> request = new HttpEntity<>(requestTapPayDto, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(TAPPAY_API_URL, request, String.class);

            log.info(response.getBody());

            Map<String, Object> isPaymentSuccess = mapper.readValue(response.getBody(), Map.class);
            int status = (int) isPaymentSuccess.get("status");
            return status == 0;

        } catch (JsonProcessingException e) {
            log.error("Error Processing TapPay API response");
            throw new RuntimeException("Error Processing Json response");
        } catch (RestClientException e) {
            log.error("Error during TayPay API call");
            throw e;
        }
    }
}
