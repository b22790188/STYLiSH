package org.example.stylish.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.userDto.response.UserInfoDto;
import org.example.stylish.userDetail.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.math.BigInteger;
import java.util.Base64;
import java.util.Date;

@Log4j2
public class JwtUtil {
    public static final Integer EXPIRATION_TIME = 1000 * 60 * 60;
    //todo: refactor secret_key with env config
    private static final String SECRET_KEY = "secret";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    private static final JWTVerifier verifier = JWT.require(algorithm).build();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String generateToken(UserInfoDto userInfoDto) {
        try {
            String userInfoJson = mapper.writeValueAsString(userInfoDto);

            return JWT.create()
                .withPayload(userInfoJson)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert user info to json");
        }

    }

    public static boolean validateToken(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public static UserInfoDto getUserInfoFromToken(String token) {
        try {

            DecodedJWT decodedJWT = verifier.verify(token);

            String payload = decodedJWT.getPayload();
            String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));

            JsonNode node = mapper.readTree(decodedPayload);
            BigInteger id = new BigInteger(node.get("id").asText());
            String provider = node.get("provider").asText();
            String name = node.get("name").asText();
            String email = node.get("email").asText();
            String picture = node.get("picture").asText();

            return new UserInfoDto(id, provider, name, email, picture);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token");
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert json to user info");
        }
    }

    public static void setAuthentication(UserInfoDto userInfoDto, HttpServletRequest request) {
        CustomUserDetails userDetails = new CustomUserDetails(userInfoDto);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
