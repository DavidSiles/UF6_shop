package model;

import java.sql.SQLException;

import dao.DaoImplJDBC;

public class Employee extends Person {

	private int employeeId;
	
	final static int USER = 123;
    private String password = "test";

	public Employee(int employeeId, String password) {
		super(password);
		this.employeeId = employeeId;
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public boolean login(int user, String password) {
    	
        DaoImplJDBC dao = new DaoImplJDBC();
        
        dao.connect();
        System.out.println("Conectado");
        Employee employee = dao.getEmployee(user, password);
        try {
			dao.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if(employee == null) {
        	return false;
        }else{
        	return true;
        }
    }
    
}
