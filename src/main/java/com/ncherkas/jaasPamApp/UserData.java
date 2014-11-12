package com.ncherkas.jaasPamApp;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * {@code UserDetails} implementation.
* Created by n.cherkas on 11/12/14 4:37 PM.
*/
public class UserData implements UserDetails {

    private final String username;
    private final String email; // may contain some additional info
    private final List<GrantedAuthority> authorities;

    public UserData(String username, String email, GrantedAuthority... authorities) {
        checkArgument(username != null);
        checkArgument(email != null);
        checkArgument(authorities != null);
        this.username = username;
        this.email = email;
        this.authorities = Collections.unmodifiableList(Arrays.asList(authorities));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
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
