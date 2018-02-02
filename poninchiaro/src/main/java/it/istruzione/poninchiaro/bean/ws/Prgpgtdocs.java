
package it.istruzione.poninchiaro.bean.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prgpgtdocs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prgpgtdocs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="prg_pgt" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="esito" type="{urn:nusoap_aut}Esito"/>
 *         &lt;element name="prg_docs" type="{urn:nusoap_aut}prgdocs" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prgpgtdocs", propOrder = {

})
public class Prgpgtdocs {

    @XmlElement(name = "prg_pgt")
    protected long prgPgt;
    @XmlElement(required = true)
    protected Esito esito;
    @XmlElement(name = "prg_docs")
    protected List<Prgdocs> prgDocs;

    /**
     * Gets the value of the prgPgt property.
     * 
     */
    public long getPrgPgt() {
        return prgPgt;
    }

    /**
     * Sets the value of the prgPgt property.
     * 
     */
    public void setPrgPgt(long value) {
        this.prgPgt = value;
    }

    /**
     * Gets the value of the esito property.
     * 
     * @return
     *     possible object is
     *     {@link Esito }
     *     
     */
    public Esito getEsito() {
        return esito;
    }

    /**
     * Sets the value of the esito property.
     * 
     * @param value
     *     allowed object is
     *     {@link Esito }
     *     
     */
    public void setEsito(Esito value) {
        this.esito = value;
    }

    /**
     * Gets the value of the prgDocs property.
     * 
     * @return
     *     possible object is
     *     {@link Prgdocs }
     *     
     */
    public List<Prgdocs> getPrgDocs() {
        return prgDocs;
    }

    /**
     * Sets the value of the prgDocs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prgdocs }
     *     
     */
    public void setPrgDocs(List<Prgdocs> value) {
        this.prgDocs = value;
    }

}
