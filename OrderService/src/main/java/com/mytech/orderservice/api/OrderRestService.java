package com.mytech.orderservice.api;

import com.mytech.orderservice.dtos.OrderDTO;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/orders")	//http://localhost:8080/OrderService/rest/orders
public class OrderRestService {
	
	@GET
	public Response getOrders() {
		return Response.ok("List orders...").build();
	}
	
	@GET
	@Path("/{id}") //http://localhost:8080/OrderService/rest/orders/1
	public Response getOrderById(@PathParam("id") int id) {
		//Skip fetch data from database before response;
		System.out.println("OrderRestService::getOrderById() -> " + id);
		return Response.ok("Info for order with id: " + id).build();
	}
	
	@POST //http://localhost:8080/OrderService/rest/orders
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrder(OrderDTO order) {
		//Skip add order to Database.
		System.out.println("New order: " + order.getId() + " - " + order.getName() );
		return Response.ok("Order with id " + order.getId() + " was created!").build();
	}
	
	@PUT //http://localhost:8080/OrderService/rest/orders
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response uploadOrder(@PathParam("id") int id, OrderDTO order) {
		//Skip update order to Database.
		System.out.println("Update order: " + order.getId() + " - " + order.getName() );
		return Response.ok("Order with id " + order.getId() + " was updated!").build();
	}
	
	@DELETE 
	@Path("/{id}") //http://localhost:8080/OrderService/rest/orders/123
	public Response uploadOrder(@PathParam("id") int id) {
		//Skip add order to Database.
		System.out.println("Delete order: " + id );
		return Response.ok("Order with id " + id + " was deleted!").build();
	}
}
