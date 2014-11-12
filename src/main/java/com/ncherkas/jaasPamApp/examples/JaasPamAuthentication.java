package com.ncherkas.jaasPamApp.examples;

import com.google.common.base.Strings;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import static com.google.common.base.Preconditions.*;

/**
 * Example of JAAS PAM API usage.
 * Created by n.cherkas on 11/5/14 7:31 AM.
 */
public class JaasPamAuthentication {

    private static final String ENTRY_NAME = "net-sf-jpam";

    private final String login;
    private final LoginContext loginContext;

    private JaasPamAuthentication(String login, LoginContext loginContext) {
        checkArgument(!Strings.isNullOrEmpty(login));
        checkArgument(loginContext != null);
        this.login = login;
        this.loginContext = loginContext;
    }

    public static JaasPamAuthentication login(String login, String password) throws LoginException {
        checkArgument(!Strings.isNullOrEmpty(login));
        checkArgument(password != null);

        LoginContext loginContext = new LoginContext(ENTRY_NAME, new LoginContextCallbackHandler(login, password));
        loginContext.login();

        return new JaasPamAuthentication(login, loginContext);
    }

    private void logout() throws LoginException {
        loginContext.logout();
    }

    public String getLogin() {
        return login;
    }

    public Subject getSubject() {
        return loginContext.getSubject();
    }
}
