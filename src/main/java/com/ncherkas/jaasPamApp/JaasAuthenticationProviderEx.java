package com.ncherkas.jaasPamApp;

import com.google.common.base.Strings;
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import java.security.Principal;

import static com.google.common.base.Preconditions.*;

/**
 * Created by n.cherkas on 11/7/14 10:10 PM.
 */
public class JaasAuthenticationProviderEx extends JaasAuthenticationProvider {

    public static final String PAM_PRINCIPAL = "PamPrincipal";

    private String loginContextName;

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

    @Override
    public void setLoginContextName(String loginContextName) {
        checkArgument(!Strings.isNullOrEmpty(loginContextName));
        this.loginContextName = loginContextName;
        super.setLoginContextName(loginContextName);
    }
}
