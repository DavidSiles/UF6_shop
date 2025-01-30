package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Amount;
import model.Employee;
import model.Product;
import model.ProductHistory;

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

	        employee = session.get(Employee.class, user);

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
		ArrayList<Product> products = null;
		try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            
            EntityManager em = session.getEntityManagerFactory().createEntityManager();
            TypedQuery<Product> query = em.createQuery("from Product", Product.class);
            products = (ArrayList<Product>) query.getResultList();
            
            
            session.getTransaction().commit();
            
            return products;
        }catch (Exception e) {
        	e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean writeInventory(ArrayList<Product> inventory) {
		try(Session session = factory.getCurrentSession()) {
			session.beginTransaction();
            
			for(Product product : inventory) {
				ProductHistory history = new ProductHistory();
	            history.setIdProducto(product);
	            history.setAvailable(product.getAvailable());
	            history.setCreatedAt(LocalDateTime.now());
	            history.setName(product.getName());
	            history.setPrice(product.getPublicPrice().getValue());
	            history.setStock(product.getStock());
	            
	            session.save(history);
			}
			
            session.getTransaction().commit();

            return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
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
