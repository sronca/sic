
package it.istruzione.poninchiaro.bean.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getdocsRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getdocsRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="prgpgt" type="{urn:nusoap_aut}prgpgt" form="unqualified"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getdocsRequestType", propOrder = {

})
public class GetdocsRequestType {

    @XmlElement(namespace = "", required = true)
    protected Prgpgt prgpgt;

    /**
     * Gets the value of the prgpgt property.
     * 
     * @return
     *     possible object is
     *     {@link Prgpgt }
     *     
     */
    public Prgpgt getPrgpgt() {
        return prgpgt;
    }

    /**
     * Sets the value of the prgpgt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prgpgt }
     *     
     */
    public void setPrgpgt(Prgpgt value) {
        this.prgpgt = value;
    }

}
