package back_end;

import java.sql.*;
import java.util.ArrayList;

import data.Account;
import data.PaymentInformation;
import data.ShoppingCart;
import data.Subscription;
import users.Buyer;

public class DatabaseHelper
{
	
	String url = "jdbc:mysql://localhost:3306";
	String user = "root";
	String password = "";
	String sql;
	Connection con;
	PreparedStatement statement;
	ResultSet rs;

	public DatabaseHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			statement = con.prepareStatement("USE ensf480projectone");
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDocument(String name, String author, int isbn, String path) {
		sql = "INSERT INTO DOCUMENTS" + " (NAME, AUTHOR, ISBN, PATH) " + "VALUES (?, ?, ?, ?) ";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, author);
			statement.setInt(3, isbn);
			statement.setString(4, path);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateDocument(int id, String name, String author, int isbn) {
		sql = "UPDATE DOCUMENTS "
			+ "SET NAME=?, "
			+ "AUTHOR=?, "
			+ "ISBN=? WHERE ID=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, author);
			statement.setInt(3, isbn);
			statement.setInt(4, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePublicationStatus(int id, boolean publication) {
		sql = "UPDATE DOCUMENTS SET PUBLICATION=? WHERE ID=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setBoolean(1, publication);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePromotionStatus(int id, boolean promotion) {
		sql = "UPDATE DOCUMENTS SET PROMOTION=? WHERE ID=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setBoolean(1, promotion);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeDocument(int id) {
		sql = "DELETE FROM DOCUMENTS WHERE ID=?";

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public int search_document_by_id(int id) {
		sql = "SELECT * FROM DOCUMENTS WHERE ID=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<Integer> search_document_by_name(String name) {
		sql = "SELECT * FROM DOCUMENTS WHERE NAME=?";
		ArrayList<Integer> results = new ArrayList<Integer>();
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, name);
			rs = statement.executeQuery();
			
			while (rs.next())
				results.add(rs.getInt("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public ArrayList<Publication> getInventory() {
		sql = "SELECT * FROM DOCUMENTS";
		ArrayList<Publication> results = new ArrayList<Publication>();
		try {
			statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
			
			while (rs.next())
				results.add(new Publication (
						rs.getInt("id"), 
						rs.getInt("isbn"), 
						rs.getString("name"), 
						rs.getString("author"), 
						rs.getInt("copies")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public ArrayList<Publication> getPromotions() {
		sql = "SELECT * FROM DOCUMENTS WHERE PROMOTION=1";
		ArrayList<Publication> results = new ArrayList<Publication>();
		try {
			statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
			
			while (rs.next())
				results.add(new Publication (
						rs.getInt("id"), 
						rs.getInt("isbn"), 
						rs.getString("name"), 
						rs.getString("author"), 
						rs.getInt("copies")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public Publication search(String name)
	{
		sql = "SELECT * FROM DOCUMENTS WHERE NAME=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, name);
			rs = statement.executeQuery();
			
			if (rs.next())
				return new Publication(rs.getInt("id"), rs.getInt("isbn"), rs.getString("name"), rs.getString("author"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setupDatabase() {
		try {
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			stmt.execute("CREATE DATABASE IF NOT EXISTS ensf480projectone");
			stmt.close();
			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTable(String name) {
		try {
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			stmt.execute("USE ensf480projectone");
			stmt.execute("CREATE TABLE IF NOT EXISTS " + name + " (" + "id BIGINT NOT NULL AUTO_INCREMENT, "
					+ "name VARCHAR(20) NOT NULL, " + "author VARCHAR(50) NOT NULL, " + "isbn INT NOT NULL, "
					+ "promotion TINYINT NOT NULL, " + "publication TINYINT NOT NULL, " + "path VARCHAR(200), "
					+ "PRIMARY KEY(id) " + ")");
			stmt.close();
			// con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkOut(ShoppingCart cart) {
		sql = "UPDATE DOCUMENTS SET COPIES=? WHERE ID=?";
		try {
			statement = con.prepareStatement(sql);
			for (int i = 0; i < cart.getCart().size(); i++) {
				statement.setInt(1, cart.getCart().get(i).getCopies() - 1);
				statement.setInt(2, cart.getCart().get(i).getId());
				statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void subscribe(String username) {
		sql = "UPDATE BUYER SET SUBSCRIPTION=1 WHERE USERNAME=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void unsubscribe(String username) {
		sql = "UPDATE BUYER SET SUBSCRIPTION=0 WHERE USERNAME=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addPaymentInfo(String username, PaymentInformation payInfo) {
		sql = "UPDATE BUYER "
			+ "SET PAYMENT=?, "
			+ "TYPE=? WHERE USERNAME=?";
			try {
				statement = con.prepareStatement(sql);
				statement.setInt(1, payInfo.getCardNumber());
				statement.setString(2, payInfo.getCardType());
				statement.setString(3, username);
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public Account getAccount(Buyer buyer) {
		sql = "SELECT * FROM BUYER WHERE USERNAME=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, buyer.getUsername());
			rs = statement.executeQuery();
			
			if (rs.next()) {
				return new Account(new ShoppingCart(null), 
								   new Subscription(rs.getBoolean("subscription")), 
								   new PaymentInformation(rs.getString("type"), rs.getInt("payment")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		ArrayList<Publication> results;
		DatabaseHelper dbh = new DatabaseHelper();
//		ShoppingCart cart = new ShoppingCart(dbh.getPromotions());
//		dbh.checkOut(cart);
		Buyer b = new Buyer("buyer1", 'o');
		System.out.println(dbh.getAccount(b).getPayInfo().getCardType());
		System.out.println(dbh.getAccount(b).getPayInfo().getCardNumber());
		System.out.println(dbh.getAccount(b).getSubscription().isValid());
		System.out.println("done");
	}
}
