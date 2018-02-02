package it.istruzione.ptof.beans;

public class BeanValueDTO extends BaseDTO {
	/**
	 *
	 */
	private static final long serialVersionUID = -8434920948378166071L;

	public BeanValueDTO() {
	};

	 
	@Override
	public String toString() {
		return "BeanValueDTO [value=" + value + ", label=" + label + "]";
	}

	public BeanValueDTO(String value, String label) {
		super();
		this.value = value;
		this.label = label;
	}

	String value, label;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanValueDTO other = (BeanValueDTO) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	
}
