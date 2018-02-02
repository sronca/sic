
package it.istruzione.poninchiaro.bean.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prgpgt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prgpgt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="prg_pgt" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prgpgt", propOrder = {

})
public class Prgpgt {

    @XmlElement(name = "prg_pgt")
    protected long prgPgt;

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

}
