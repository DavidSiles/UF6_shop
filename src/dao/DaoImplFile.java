package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Shop;
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
		int count = 0;
		boolean exit = false;
		String x = null; String y = null; String z = null;
		
		try {
        	//create file
			/*
			File fileInventory = new File("inputInventory.txt");
			if(fileInventory.exists()) {
			*/
				FileReader fr = new FileReader("inputInventory.txt");
				BufferedReader br = new BufferedReader(fr);
				while(exit == false) {
            		String myLine = br.readLine();
            		if(myLine != null) {
		            	String[] result1 = myLine.split(";");
		            	while(count<3) {
			            	String[] result2 = result1[count].split(":");
			            	if(count == 0) {
			            		 x = result2[1];
			            	}else if(count == 1) {
			            		 y = result2[1];
			            	}else if(count == 2) {
			            		 z = result2[1];
			            	}
			            	count++;
		            	}
		            	double price = Double.parseDouble(y);
		            	int stock = Integer.parseInt(z);
		            	inventoryLoader.add(new Product(x, price, true, stock));
		            	count = 0;
            		}else {
            			exit = true;
            		}
				}
	            	fr.close();
	        		br.close();
            
        } catch (IOException e) {
            System.out.println("Error: Archivo no encontrado");
            e.printStackTrace();
        }
		
		return inventoryLoader;

	}

	
	public boolean writeInventory(ArrayList<Product> inventory) {
		boolean isWrited = false;
		try {
			File fileInventory = new File("inputInventory.txt");
			FileWriter myWriter = new FileWriter("inputInventory.txt"); 
			if(fileInventory.exists()) {		
				for (Product product : inventory) {
	    			if (product != null) {
	    				myWriter.write("Product:"+product.getName()+";Wholesaler Price:"
	    			+product.getWholesalerPrice()+";Stock:"+product.getStock()+";\n");  
	    			}
				}        			        		
    System.out.println("File inventory finished");
    myWriter.close();
    isWrited = true;
			}
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
