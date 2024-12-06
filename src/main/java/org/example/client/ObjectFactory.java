
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

    private final static QName _CanBorrowBookResponse_QNAME = new QName("http://service.example.org/", "canBorrowBookResponse");
    private final static QName _CanBorrowBook_QNAME = new QName("http://service.example.org/", "canBorrowBook");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CanBorrowBook }
     * 
     */
    public CanBorrowBook createCanBorrowBook() {
        return new CanBorrowBook();
    }

    /**
     * Create an instance of {@link CanBorrowBookResponse }
     * 
     */
    public CanBorrowBookResponse createCanBorrowBookResponse() {
        return new CanBorrowBookResponse();
    }

    /**
     * Create an instance of {@link Reader }
     * 
     */
    public Reader createReader() {
        return new Reader();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanBorrowBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "canBorrowBookResponse")
    public JAXBElement<CanBorrowBookResponse> createCanBorrowBookResponse(CanBorrowBookResponse value) {
        return new JAXBElement<CanBorrowBookResponse>(_CanBorrowBookResponse_QNAME, CanBorrowBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanBorrowBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.example.org/", name = "canBorrowBook")
    public JAXBElement<CanBorrowBook> createCanBorrowBook(CanBorrowBook value) {
        return new JAXBElement<CanBorrowBook>(_CanBorrowBook_QNAME, CanBorrowBook.class, null, value);
    }

}
