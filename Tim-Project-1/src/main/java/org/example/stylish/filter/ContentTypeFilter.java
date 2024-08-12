package org.example.stylish.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.stylish.dto.ErrorResponseDto;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ContentTypeFilter extends OncePerRequestFilter {
    private static final String REQUEST_CONTENT_TYPE = "application/json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        if (!REQUEST_CONTENT_TYPE.equalsIgnoreCase(request.getContentType())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");

            ErrorResponseDto errorResponseDto = new ErrorResponseDto("Unsupported Content-Type. Required:" + REQUEST_CONTENT_TYPE);
            String jsonResponse = objectMapper.writeValueAsString(errorResponseDto);

            response.getWriter().write(jsonResponse);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
