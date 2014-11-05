package com.ncherkas.jaasPamApp.authentication;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * Created by n.cherkas on 11/5/14 7:31 AM.
 */
public class JaasPamAuthentication {

    private final LoginContext loginContext;

    private JaasPamAuthentication(LoginContext loginContext) {
        this.loginContext = loginContext;
    }

    public static JaasPamAuthentication login(String login, String password) throws LoginException {
        LoginContext loginContext = new LoginContext("net-sf-jpam", new LoginContextCallbackHandler(login, password));
        loginContext.login();
        return new JaasPamAuthentication(loginContext);
    }

    private void logout() throws LoginException {
        loginContext.logout();
    }

    public Subject getSubject() {
        return loginContext.getSubject();
    }
}
