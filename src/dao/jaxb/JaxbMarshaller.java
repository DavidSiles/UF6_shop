package dao.jaxb;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import model.ProductList;

public class JaxbMarshaller {

	public void init (ProductList products) {
		try {
			JAXBContext context = JAXBContext.newInstance(ProductList.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			System.out.println("marshalling... ");
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			marshaller.marshal(products, new File("files/inventory_"+date+".xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
