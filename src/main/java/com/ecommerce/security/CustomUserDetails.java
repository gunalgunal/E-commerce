package com.ecommerce.security;

import com.ecommerce.entity.Role;
import com.ecommerce.entity.UserInformation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class CustomUserDetails implements UserDetails {


    private UserInformation user;
    public CustomUserDetails()
    {

    }
    public CustomUserDetails(UserInformation user)
    {

        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authority=new ArrayList<>();
        for(Role role: user.getRoles())
        {
            authority.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
        }
        return authority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUserName();
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
