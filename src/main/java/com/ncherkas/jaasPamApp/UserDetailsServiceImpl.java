package com.ncherkas.jaasPamApp;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

/**
 * This service provides info about users known to the application. In other words this service provides info about
 * users of the Unix system this application is running on. Username here must be equal to login of Unix user.
 * This service can be implemented using database.
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
