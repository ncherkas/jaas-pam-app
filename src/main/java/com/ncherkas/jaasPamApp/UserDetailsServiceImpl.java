package com.ncherkas.jaasPamApp;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

/**
 * Service provides info about users known to the application. This service can be implemented using database.
 *
 * Created by n.cherkas on 11/11/14 7:08 AM.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Map<String, UserData> USERS = Collections.unmodifiableMap(new HashMap<String, UserData>() {{
        /**
         * Authorization is made using roles set by {@code JPamAuthorityGranter} so the value set here in
         * {@code SimpleGrantedAuthority} is not used
         */
        put("app_usr1", new UserData("app_usr1", "app_usr1@host.com", new SimpleGrantedAuthority("ROLE_USER")));
        put("app_usr2", new UserData("app_usr2", "app_usr2@host.com", new SimpleGrantedAuthority("ROLE_USER")));
    }});

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = USERS.get(username);
        if (userData == null) {
            throw new UsernameNotFoundException("Unknown user '" + username + "'");
        }
        return userData;
    }
}
