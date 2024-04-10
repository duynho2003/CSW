package com.mytech.orderservice.filters;

import java.security.Principal;

import com.mytech.orderservice.models.User;

import jakarta.ws.rs.core.SecurityContext;

public class BasicSecurityConext implements SecurityContext {

	private User user;

	public BasicSecurityConext(User user) {
		this.user = user;
	}

	@Override
	public Principal getUserPrincipal() {
		return () -> user.getUsername();
	}

	@Override
	public boolean isUserInRole(String role) {
		return user.getRoles().contains(role);
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}
}