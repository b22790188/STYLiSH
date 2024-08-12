package org.example.stylish.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.ErrorResponseDto;
import org.example.stylish.dto.userDto.response.UserInfoDto;
import org.example.stylish.utils.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");


        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                if (JwtUtil.validateToken(token)) {
                    UserInfoDto userInfoDto = JwtUtil.getUserInfoFromToken(token);
                    JwtUtil.setAuthentication(userInfoDto, request);
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json");
                    ErrorResponseDto errorResponseDto = new ErrorResponseDto("Invalid token");
                    String jsonResponse = objectMapper.writeValueAsString(errorResponseDto);

                    response.getWriter().write(jsonResponse);
                    return;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                ErrorResponseDto errorResponseDto = new ErrorResponseDto("Invalid token");
                String jsonResponse = objectMapper.writeValueAsString(errorResponseDto);

                response.getWriter().write(jsonResponse);
                return;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ErrorResponseDto errorResponseDto = new ErrorResponseDto("No token provided");
            String jsonResponse = objectMapper.writeValueAsString(errorResponseDto);

            response.getWriter().write(jsonResponse);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
