package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Products")
public class ProductList {
    private List<Product> products;
    
    
    private int total;
    
    public ProductList() {
        this.products = new ArrayList<>();
    }

    public ProductList(ArrayList<Product> products) {
		this.products = products;
	}
    
    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    @Override
    public String toString() {
        return "Products [products=" + products + "]";
    }
    
    @XmlAttribute(name = "total")
	public int getTotal() {
    	return products.size();
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
