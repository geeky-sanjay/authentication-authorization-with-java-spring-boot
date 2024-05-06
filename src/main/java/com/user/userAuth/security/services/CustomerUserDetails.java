package com.user.userAuth.security.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.user.userAuth.models.Role;
import com.user.userAuth.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerUserDetails implements UserDetails {
    private String userName;
    private String password;
    private List<GrantedAuthority> authorityList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    public CustomerUserDetails() {
    }

    public CustomerUserDetails(User user){
        this.userName = user.getEmail();
        this.password = user.getHashPassword();
        for(Role role : user.getRoles()){
            authorityList.add(new CustomGrantedAuthority(role));
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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