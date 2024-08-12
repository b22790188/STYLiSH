package org.example.stylish.userDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.stylish.dto.userDto.response.UserInfoDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final UserInfoDto userInfoDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        //Because jwt authentication don't need password
        return "";
    }

    @Override
    public String getUsername() {
        return userInfoDto.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
