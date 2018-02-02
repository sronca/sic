
package it.istruzione.poninchiaro.bean.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getdocsResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getdocsResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="prgpgtdocs" type="{urn:nusoap_aut}prgpgtdocs" form="unqualified"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getdocsResponseType", propOrder = {

})
public class GetdocsResponseType {

    @XmlElement(namespace = "", required = true)
    protected Prgpgtdocs prgpgtdocs;

    /**
     * Gets the value of the prgpgtdocs property.
     * 
     * @return
     *     possible object is
     *     {@link Prgpgtdocs }
     *     
     */
    public Prgpgtdocs getPrgpgtdocs() {
        return prgpgtdocs;
    }

    /**
     * Sets the value of the prgpgtdocs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prgpgtdocs }
     *     
     */
    public void setPrgpgtdocs(Prgpgtdocs value) {
        this.prgpgtdocs = value;
    }

}
