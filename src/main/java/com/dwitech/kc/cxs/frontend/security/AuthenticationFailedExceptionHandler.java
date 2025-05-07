package com.dwitech.kc.cxs.frontend.security;

import com.dwitech.kc.cxs.frontend.boundary.Security;
import io.quarkiverse.renarde.router.Router;
import io.quarkus.security.AuthenticationFailedException;

import jakarta.annotation.Priority;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.net.URI;

import static io.quarkiverse.renarde.router.Router.getURI;
import static jakarta.ws.rs.core.Response.temporaryRedirect;

@Provider
@Priority(1)
public class AuthenticationFailedExceptionHandler implements ExceptionMapper<AuthenticationFailedException>  {

	@Override
	public Response toResponse(AuthenticationFailedException exception) {
		var removeCookie = new NewCookie.Builder("Authorization")
				.value(null)
				.path("/")
				.domain(null)
				.comment(null)
				.maxAge(0)
				.secure(false)
				.httpOnly(true)
				.build();
		var uri = getURI(Security::login);
		return temporaryRedirect(uri).cookie(removeCookie).build();
	}
}