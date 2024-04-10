package com.mytech.orderservice.api;

import com.mytech.orderservice.helper.JwTokenHelper;
import com.mytech.orderservice.models.User;
import com.mytech.orderservice.services.UserService;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
public class AuthRestService {
	
	private UserService userService = new UserService();

	@POST
	public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
		System.out.println("Username: " + username + " - Password: " + password);
		//Fetch user from database to check validation.
		User user = userService.getUser(username);
		//Check user is available then check password
		if (!user.getPassword().equals(password)) {
			return Response.status(Status.FORBIDDEN).entity("Username or password is not valid!").build();
		}
		
		String token = JwTokenHelper.createJWT(user);
		
		return Response.ok("Token: " + token).build();
	}
}
