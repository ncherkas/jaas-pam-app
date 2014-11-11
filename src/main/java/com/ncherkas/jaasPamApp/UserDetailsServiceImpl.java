package com.ncherkas.jaasPamApp;

import com.google.common.base.Preconditions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by n.cherkas on 11/11/14 7:08 AM.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Map<String, UserData> USERS = Collections.unmodifiableMap(new HashMap<String, UserData>() {{
        put("app_usr", new UserData("app_usr", "app_usr@host.com"));
    }});

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = USERS.get(username);
        if (userData == null) {
            throw new UsernameNotFoundException("Unknown user '" + username + "'");
        }
        return userData;
    }

    public static class UserData implements UserDetails {

        private final String username;
        private final String email;

        public UserData(String username, String email) {
            Preconditions.checkArgument(username != null);
            Preconditions.checkArgument(email != null);
            this.username = username;
            this.email = email;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
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
}
