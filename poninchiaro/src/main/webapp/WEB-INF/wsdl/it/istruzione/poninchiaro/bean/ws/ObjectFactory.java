
package it.istruzione.poninchiaro.bean.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.istruzione.poninchiaro.bean.ws package. 
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

    private final static QName _GetdocsResponse_QNAME = new QName("urn:nusoap_aut", "getdocsResponse");
    private final static QName _Getdocs_QNAME = new QName("urn:nusoap_aut", "getdocs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.istruzione.poninchiaro.bean.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetdocsRequestType }
     * 
     */
    public GetdocsRequestType createGetdocsRequestType() {
        return new GetdocsRequestType();
    }

    /**
     * Create an instance of {@link GetdocsResponseType }
     * 
     */
    public GetdocsResponseType createGetdocsResponseType() {
        return new GetdocsResponseType();
    }

    /**
     * Create an instance of {@link Prgdocs }
     * 
     */
    public Prgdocs createPrgdocs() {
        return new Prgdocs();
    }

    /**
     * Create an instance of {@link Esito }
     * 
     */
    public Esito createEsito() {
        return new Esito();
    }

    /**
     * Create an instance of {@link Prgpgtdocs }
     * 
     */
    public Prgpgtdocs createPrgpgtdocs() {
        return new Prgpgtdocs();
    }

    /**
     * Create an instance of {@link Prgpgt }
     * 
     */
    public Prgpgt createPrgpgt() {
        return new Prgpgt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetdocsResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:nusoap_aut", name = "getdocsResponse")
    public JAXBElement<GetdocsResponseType> createGetdocsResponse(GetdocsResponseType value) {
        return new JAXBElement<GetdocsResponseType>(_GetdocsResponse_QNAME, GetdocsResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetdocsRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:nusoap_aut", name = "getdocs")
    public JAXBElement<GetdocsRequestType> createGetdocs(GetdocsRequestType value) {
        return new JAXBElement<GetdocsRequestType>(_Getdocs_QNAME, GetdocsRequestType.class, null, value);
    }

}
