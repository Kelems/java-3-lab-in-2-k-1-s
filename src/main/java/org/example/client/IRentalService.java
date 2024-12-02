
package org.example.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IRentalService", targetNamespace = "http://service.example.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IRentalService {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rentBook", targetNamespace = "http://service.example.org/", className = "org.example.client.RentBook")
    @ResponseWrapper(localName = "rentBookResponse", targetNamespace = "http://service.example.org/", className = "org.example.client.RentBookResponse")
    @Action(input = "http://service.example.org/IRentalService/rentBookRequest", output = "http://service.example.org/IRentalService/rentBookResponse")
    public boolean rentBook(
        @WebParam(name = "arg0", targetNamespace = "")
        Reader arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Book arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        boolean arg2);

}