package com.dwitech.kc.cxs.frontend.security;

import io.quarkus.security.AuthenticationFailedException;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named("loginInfo")
public class LoginInfo {
	@Inject JsonWebToken token;

	public boolean isLoggedIn() {
		try {
			return token.getGroups() != null && !token.getGroups().isEmpty();
		} catch (AuthenticationFailedException afExc) {
			return false;
		}
	}
}