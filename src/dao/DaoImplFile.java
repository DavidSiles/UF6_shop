package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Shop;
import model.Amount;
import model.Employee;
import model.Product;

public class DaoImplFile implements Dao{

	public void connect() {
		// TODO Auto-generated method stub
		
	}

	
	public void disconnect() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	public ArrayList<Product> getInventory() {
		
		ArrayList<Product> inventoryLoader = new ArrayList<Product>();
		
		
		try {
			File f = new File(System.getProperty("user.dir") + File.separator + "files/inputInventory.txt");
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine();

			while (line != null) {
				String[] sections = line.split(";");
				
				String name = "";
				double wholesalerPrice=0.0;
				int stock = 0;
				
				for (int i = 0; i < sections.length; i++) {
					String[] data = sections[i].split(":");
					
					switch (i) {
					case 0:
						name = data[1];
						break;
						
					case 1:
						wholesalerPrice = Double.parseDouble(data[1]);
						break;
						
					case 2:
						stock = Integer.parseInt(data[1]);
						break;
						
					default:
						break;
					}
				}
				inventoryLoader.add(new Product(name, new Amount(wholesalerPrice), true, stock));
				line = br.readLine();
			}
			br.close();
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inventoryLoader;

	}

	
	public boolean writeInventory(ArrayList<Product> inventory) {
		boolean isWrited = false;
		try {	
				FileWriter myWriter = new FileWriter("inputInventory.txt"); 
				for (Product product : inventory) {
	    			if (product != null) {
	    				myWriter.write("Product:"+product.getName()+";Wholesaler Price:"
	    			+product.getWholesalerPrice()+";Stock:"+product.getStock()+";\n");  
	    			}
				}        			        		
    System.out.println("File inventory finished");
    myWriter.close();
    isWrited = true;

		} catch (IOException e) {
            System.out.println("Error: Archivo no encontrado");
            e.printStackTrace();
        }
		return isWrited;
	}

	public Employee getEmployee(int employeeId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
