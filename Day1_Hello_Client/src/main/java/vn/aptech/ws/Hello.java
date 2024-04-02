package vn.aptech.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 4.0.0
 * 2024-04-01T16:48:05.637+07:00
 * Generated source version: 4.0.0
 *
 */
@WebService(targetNamespace = "http://ws.aptech.vn/", name = "Hello")
@XmlSeeAlso({ObjectFactory.class})
public interface Hello {

    @WebMethod
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://ws.aptech.vn/", className = "vn.aptech.ws.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://ws.aptech.vn/", className = "vn.aptech.ws.SayHelloResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sayHello(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
