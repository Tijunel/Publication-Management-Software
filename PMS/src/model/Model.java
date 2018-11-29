package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import control.Control;

public abstract class Model {
	String url = "jdbc:mysql://localhost:3306";
	String user = "root";
	String password = "";
	String sql;
	Connection con;
	PreparedStatement statement;
	ResultSet rs;
	@SuppressWarnings("unused")
	private Control control;
	
	public Model(Control control){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			statement = con.prepareStatement("USE ensf480projectone");
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.control = control;
	}
}
