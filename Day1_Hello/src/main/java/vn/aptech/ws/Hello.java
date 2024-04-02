package vn.aptech.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(serviceName = "hello")
public class Hello {
	@WebMethod
	public String sayHello(String name) {
		return "Hello" + name;
	}
}
