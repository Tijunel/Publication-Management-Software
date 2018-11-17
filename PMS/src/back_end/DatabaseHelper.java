package back_end;
import java.sql.*;

public class DatabaseHelper
{
	
	String url = "jdbc:mysql://localhost:3306";
	String user = "root";
	String password = "";
	
	public void setupDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			stmt.execute("CREATE DATABASE IF NOT EXISTS ensf480projectone");
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTable(String name) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			stmt.execute("USE ensf480projectone");
			stmt.execute("CREATE TABLE IF NOT EXISTS " + name + " ("
					+ "id BIGINT NOT NULL AUTO_INCREMENT, "
					+ "username VARCHAR(10) NOT NULL, "
					+ "password VARCHAR(10) NOT NULL, "
					+ "type VARCHAR(10) NOT NULL, "
					+ "email VARCHAR(50), "
					+ "PRIMARY KEY(id) "
					+ ")");
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		DatabaseHelper dbh = new DatabaseHelper();
		dbh.setupDatabase();
		dbh.createTable("staff");
		System.out.println("done");
	}
}
