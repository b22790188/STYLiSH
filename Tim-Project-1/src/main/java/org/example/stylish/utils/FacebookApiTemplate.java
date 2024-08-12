package org.example.stylish.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.example.stylish.exception.WrongFBTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
public class FacebookApiTemplate {
    @Autowired
    private ObjectMapper objectMapper;

    private static final String FACEBOOK_API_URL = "https://graph.facebook.com/me?fields=id,name,email,picture&access_token={access_token}";

    public JsonNode getUserInfo(String accessToken) {
        String url = FACEBOOK_API_URL.replace("{access_token}", accessToken);
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            return objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse Json response");
        } catch (RestClientException e) {
            throw new WrongFBTokenException("FB Token invalid!");
        }
    }
}
