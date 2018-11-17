package back_end;

import java.sql.*;
import java.util.ArrayList;

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

	public static void main(String[] args) {
		ArrayList<Publication> results;
		DatabaseHelper dbh = new DatabaseHelper();
		results = dbh.getPromotions();		
		Inventory inv = new Inventory(results);
		for (int i = 0; i < results.size(); i++) {
			System.out.println(inv.getPublications().get(i).getId());
			System.out.println(inv.getPublications().get(i).getTitle());
			System.out.println(inv.getPublications().get(i).getCopies());
			System.out.println();
		}
		System.out.println("done");
	}
}
