package com.ncherkas.jaasPamApp;

import com.google.common.base.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import java.security.Principal;

import static com.google.common.base.Preconditions.*;

/**
 * Custom JAAS PAM {@code AuthenticationProvider}.
 * TODO: check the behaviour of method {@code handleLogout}
 * Created by n.cherkas on 11/7/14 10:10 PM.
 */
public class JaasAuthenticationProviderEx extends JaasAuthenticationProvider {

    // Default principle for JAAS PAM authentication
    public static final String PAM_PRINCIPAL = "PamPrincipal";

    private String loginContextName;
    private UserDetailsService userDetailsService;

    /**
     * JAAS PAM module {@code net.sf.jpam.jaas.JpamLoginModule} doesn't collect any principals so we add them manually.
     * @param handler
     * @return
     * @throws LoginException
     */
    @Override
    protected LoginContext createLoginContext(CallbackHandler handler) throws LoginException {
        checkState(!Strings.isNullOrEmpty(loginContextName));
        Subject subject = new Subject();
        subject.getPrincipals().add(new Principal() {
            @Override
            public String getName() {
                return PAM_PRINCIPAL;
            }
        });
        return new LoginContext(loginContextName, subject, handler);
    }

    /**
     * Authentication is made in two phases:
     *  1) {@code JaasAuthenticationProviderEx} checks whether the user is known to the application,
     *      {@code UserDetailsServiceImpl} is used at this stage;
     *  2) {@code JaasAuthenticationProviderEx} performs the actual authentication using JAAS PAM module
     *      {@code net.sf.jpam.jaas.JpamLoginModule}.
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        checkState(userDetailsService != null);

        if (!(auth instanceof UsernamePasswordAuthenticationToken)) {
            return null;
        }

        Object principal = auth.getPrincipal();
        String username = principal instanceof UserDetails
                ? ((UserDetails) principal).getUsername()
                : principal.toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        ((UsernamePasswordAuthenticationToken) auth).setDetails(userDetails);

        return super.authenticate(auth);
    }

    @Override
    public void setLoginContextName(String loginContextName) {
        checkArgument(!Strings.isNullOrEmpty(loginContextName));
        this.loginContextName = loginContextName;
        super.setLoginContextName(loginContextName);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        checkArgument(userDetailsService != null);
        this.userDetailsService = userDetailsService;
    }
}
