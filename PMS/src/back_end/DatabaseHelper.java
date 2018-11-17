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
		ArrayList<Integer> results;
		DatabaseHelper dbh = new DatabaseHelper();
		dbh.setupDatabase();
		dbh.createTable("documents");
//		dbh.removeDocument(7);
//		dbh.addDocument("Inkheart", "Cornelia Funke", 12345678, "none yet");
//		dbh.removeDocument(2);
//		dbh.addDocument("Eragon", "Christopher Paolini", 87654321, "none yet");
		System.out.println("Id is: " + dbh.search_document_by_id(8));
		System.out.println("Id is: " + dbh.search_document_by_id(9));
		System.out.println("Id is: " + dbh.search_document_by_id(1));
		results = dbh.search_document_by_name("inkheart");
		for(int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
		System.out.println();
		results = dbh.search_document_by_name("eragon");
		for(int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
		results = dbh.search_document_by_name("null");
		for(int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
		dbh.updateDocument(10, "Eldest", "Christopher Paolini", 7654321);
		dbh.updatePromotionStatus(10, true);
		dbh.updatePublicationStatus(10, true);
		System.out.println("done");
	}
}
