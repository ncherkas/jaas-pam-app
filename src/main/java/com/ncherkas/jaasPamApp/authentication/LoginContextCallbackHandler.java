package com.ncherkas.jaasPamApp.authentication;

import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * Created by n.cherkas on 11/5/14 7:34 AM.
 */
public class LoginContextCallbackHandler implements CallbackHandler {

    private final String login;
    private final String password;

    public LoginContextCallbackHandler(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof TextOutputCallback) {
                // Display the message according to the specified type
                TextOutputCallback toc = (TextOutputCallback) callback;
                switch (toc.getMessageType()) {
                    case TextOutputCallback.INFORMATION:
                        System.out.println("INFORMATION: " + toc.getMessage());
                        break;
                    case TextOutputCallback.ERROR:
                        System.out.println("ERROR: " + toc.getMessage());
                        break;
                    case TextOutputCallback.WARNING:
                        System.out.println("WARNING: " + toc.getMessage());
                        break;
                    default:
                        throw new IOException("Unsupported message type: " + toc.getMessageType());
                }
            } else if (callback instanceof NameCallback) {
                // Prompt the user for a username
                NameCallback nc = (NameCallback) callback;
                nc.setName(login);
            } else if (callback instanceof PasswordCallback) {
                // Prompt the user for sensitive information
                PasswordCallback pc = (PasswordCallback) callback;
                pc.setPassword(password.toCharArray());
            } else {
                throw new UnsupportedCallbackException(callback, "Unrecognized Callback");
            }
        }
    }
}
