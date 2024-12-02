
package org.example.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ReaderServiceService", targetNamespace = "http://service.example.org/", wsdlLocation = "http://127.0.0.1:8081/reader?wsdl")
public class ReaderServiceService
    extends Service
{

    private final static URL READERSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException READERSERVICESERVICE_EXCEPTION;
    private final static QName READERSERVICESERVICE_QNAME = new QName("http://service.example.org/", "ReaderServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8081/reader?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        READERSERVICESERVICE_WSDL_LOCATION = url;
        READERSERVICESERVICE_EXCEPTION = e;
    }

    public ReaderServiceService() {
        super(__getWsdlLocation(), READERSERVICESERVICE_QNAME);
    }

    public ReaderServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), READERSERVICESERVICE_QNAME, features);
    }

    public ReaderServiceService(URL wsdlLocation) {
        super(wsdlLocation, READERSERVICESERVICE_QNAME);
    }

    public ReaderServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, READERSERVICESERVICE_QNAME, features);
    }

    public ReaderServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ReaderServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IReaderService
     */
    @WebEndpoint(name = "ReaderServicePort")
    public IReaderService getReaderServicePort() {
        return super.getPort(new QName("http://service.example.org/", "ReaderServicePort"), IReaderService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IReaderService
     */
    @WebEndpoint(name = "ReaderServicePort")
    public IReaderService getReaderServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.example.org/", "ReaderServicePort"), IReaderService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (READERSERVICESERVICE_EXCEPTION!= null) {
            throw READERSERVICESERVICE_EXCEPTION;
        }
        return READERSERVICESERVICE_WSDL_LOCATION;
    }

}
