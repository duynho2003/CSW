package com.mytech.orderservice.services;

import java.util.ArrayList;
import java.util.Arrays;

import com.mytech.orderservice.models.IRole;
import com.mytech.orderservice.models.User;

public class UserService {

	public User getUser(String username) {
		User user = new User();
		user.setUsername(username);
		user.setPassword("123");
		if ("admin".equals(username)) {
			user.setRoles(Arrays.asList(IRole.ROLE_ADMIN));
		} else if ("customer".equals(username)) {
			user.setRoles(Arrays.asList(IRole.ROLE_CUSTOMER));
		} else if ("mixin".equals(username)) {
			user.setRoles(Arrays.asList(IRole.ROLE_ADMIN, IRole.ROLE_CUSTOMER));
		} else {
			user.setRoles(new ArrayList<>());
		}
		return user;
	}
}