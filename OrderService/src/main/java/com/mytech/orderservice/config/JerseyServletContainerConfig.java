package com.mytech.orderservice.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class JerseyServletContainerConfig extends ResourceConfig {
	public JerseyServletContainerConfig() {
		// if there are more than two packages then separate them with semicolon
		packages("com.mytech.orderservice");
		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO,
				LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
		register(JacksonFeature.class);
		
		// This authorization feature is not automatically turned on.
		// We need to turn it on by ourself.
		register(RolesAllowedDynamicFeature.class);
	}
}