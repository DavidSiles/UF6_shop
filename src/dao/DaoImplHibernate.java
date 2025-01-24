package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Amount;
import model.Employee;
import model.Product;

public class DaoImplHibernate implements Dao{

	private Connection conn;
	private SessionFactory factory;
	
	@Override
	public void connect() {
		String url = "jdbc:mysql://localhost:3306/SHOPSILES3";
		String user = "root";
		String pass = "";
		try {
			this.conn = DriverManager.getConnection(url, user, pass);
			factory = new Configuration().configure("main/resources/hibernate.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee getEmployee(int user, String password) {
	    Employee employee = null;
	    try (Session session = factory.openSession()) {
	        session.beginTransaction();

	        String hql = "from Employee where user = :user and password = :password";
	        employee = session.createQuery(hql, Employee.class)
	                          .setParameter("user", user)
	                          .setParameter("password", password)
	                          .uniqueResult();

	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return employee;
	}

	@Override
	public void disconnect() {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Product> getInventory() {
		try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product", Product.class).getResultList();
            session.getTransaction().commit();

            return (ArrayList<Product>) products;
        }
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		
		return false;
	}

	@Override
	public boolean addProduct(String name, Amount price, int stock, boolean avaiblable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addStockProduct(String name, int stock) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProduct(String name) {
		// TODO Auto-generated method stub
		return false;
	}

}
