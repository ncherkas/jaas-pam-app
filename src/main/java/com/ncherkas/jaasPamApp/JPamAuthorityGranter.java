package com.ncherkas.jaasPamApp;

import org.springframework.security.authentication.jaas.AuthorityGranter;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.*;
import static com.ncherkas.jaasPamApp.JaasAuthenticationProviderEx.PAM_PRINCIPAL;

/**
 * Provides roles for users authenticated using JAAS PAM mechanism.
 * Created by n.cherkas on 11/7/14 7:21 PM.
 */
public class JPamAuthorityGranter implements AuthorityGranter {

    private static final Set<String> ROLES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("ROLE_USER")));

    @Override
    public Set<String> grant(Principal principal) {
        checkArgument(principal != null);
        return PAM_PRINCIPAL.equals(principal.getName()) ? ROLES : Collections.<String>emptySet();
    }
}
