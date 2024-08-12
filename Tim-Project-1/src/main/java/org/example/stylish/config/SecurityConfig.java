package org.example.stylish.config;

import jakarta.annotation.Nonnull;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.example.stylish.filter.ContentTypeFilter;
import org.example.stylish.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        OrRequestMatcher jsonContentTypeApiMatcher = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/1.0/order/checkout"),
            new AntPathRequestMatcher("/api/1.0/user/signup"),
            new AntPathRequestMatcher("/api/1.0/user/signin")
        );

        OrRequestMatcher publicApiMatcher = new OrRequestMatcher(
            jsonContentTypeApiMatcher,
            new AntPathRequestMatcher("/admin/**"),
            new AntPathRequestMatcher("/*.html"),
            new AntPathRequestMatcher("/css/**"),
            new AntPathRequestMatcher("/js/**"),
            new AntPathRequestMatcher("/asset/**"),
            new AntPathRequestMatcher("/marketing/**"),
            new AntPathRequestMatcher("/api/**")
        );

        OrRequestMatcher jwtApiMatcher = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/1.0/user/profile"),
            new AntPathRequestMatcher("/api/1.0/order/checkout")
        );

        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers(publicApiMatcher).permitAll()
                .anyRequest().authenticated()
            ).csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(new ContentTypeFilter() {
                @Override
                protected boolean shouldNotFilter(@Nonnull HttpServletRequest request) throws ServletException {
                    return !jsonContentTypeApiMatcher.matches(request);
                }
            }, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JwtAuthenticationFilter() {
                @Override
                protected boolean shouldNotFilter(@Nonnull HttpServletRequest request) throws ServletException {
                    return !jwtApiMatcher.matches(request);
                }
            }, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
