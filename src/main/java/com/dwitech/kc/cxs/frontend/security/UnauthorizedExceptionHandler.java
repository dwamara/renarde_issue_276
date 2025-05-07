package com.dwitech.kc.cxs.frontend.security;

import io.quarkus.security.UnauthorizedException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnauthorizedExceptionHandler implements ExceptionMapper<UnauthorizedException> {
	@Override
	public Response toResponse(UnauthorizedException exception) {
		return Response.status(200).entity("Unauthorized Access!").build();
	}
}