package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="price")
public class Amount {
	
	private double value;
	final static String CURRENCY = "€";
	
	public Amount(double value) {
		super();
		this.value = value;
	}
	@XmlValue
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public static String getCurrency() {
		return CURRENCY;
	}

	@Override
	public String toString() {
		return value + CURRENCY;
	}
	
	
	
}
