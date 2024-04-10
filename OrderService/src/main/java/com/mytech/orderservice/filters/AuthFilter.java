package com.mytech.orderservice.filters;

import java.io.IOException;

import com.mytech.orderservice.filters.BasicSecurityConext;
import com.mytech.orderservice.helper.JwTokenHelper;
import com.mytech.orderservice.models.User;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION) // needs to happen before authorization
public class AuthFilter implements ContainerRequestFilter {

	private static final String REALM = "mytech";
	private static final String AUTHENTICATION_SCHEME = "Bearer";

	/**
	 * Extracting the token from the request and validating it
	 * 
	 * The client should send the token in the standard HTTP Authorization header of
	 * the request. For example: Authorization: Bearer <token-goes-here>
	 * 
	 * Finally, set the security context of the current request
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// (1) Get Token Authorization from the header
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// (2) Validate the Authorization header
		if (!isTokenBasedAuthentication(authorizationHeader)) {
			return;
		}

		// (3) Extract the token from the Authorization header
		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

		try {

			// (4) Validate the token
			if (JwTokenHelper.isTokenExpired(token)) {
				abortWithUnauthorized(requestContext);
				return;
			}

			// (5) Getting the User information from token
			User user = JwTokenHelper.getUserFromToken(token);

			System.out.println("UserFilter: username: " + user.getUsername());
			
			// (6) Overriding the security context of the current request
			SecurityContext oldContext = requestContext.getSecurityContext();
			requestContext.setSecurityContext(new BasicSecurityConext(user));
		} catch (Exception e) {
			abortWithUnauthorized(requestContext);
		}
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		return authorizationHeader != null
				&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext) {

		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		Response respone = Response.status(Response.Status.UNAUTHORIZED) // 401 Unauthorized
				.header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
				.entity("You cannot access this resource") // the response entity
				.build();
		requestContext.abortWith(respone);
	}
}