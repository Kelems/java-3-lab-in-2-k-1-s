
package org.example.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RentBook_QNAME = new QName("http://service.example.org/", "rentBook");
    private final static QName _RentBookResponse_QNAME = new QName("http://service.example.org/", "rentBookResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RentBook }
     * 
     */
    public RentBook createRentBook() {
        return new RentBook();
    }

    /**
     * Create an instance of {@link RentBookResponse }
     * 
     */
    public RentBookResponse createRentBookResponse() {
        return new RentBookResponse();
    }

    /**
     * Create an instance of {@link Reader }
     * 
     */
    public Reader createReader() {
        return new Reader();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RentBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "rentBook")
    public JAXBElement<RentBook> createRentBook(RentBook value) {
        return new JAXBElement<RentBook>(_RentBook_QNAME, RentBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RentBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "rentBookResponse")
    public JAXBElement<RentBookResponse> createRentBookResponse(RentBookResponse value) {
        return new JAXBElement<RentBookResponse>(_RentBookResponse_QNAME, RentBookResponse.class, null, value);
    }

}
