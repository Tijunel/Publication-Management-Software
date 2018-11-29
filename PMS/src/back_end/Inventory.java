package back_end;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Inventory implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Publication> publications;
	
	public Inventory(ArrayList<Publication> publications){
		this.setPublications(publications);
	}

	public ArrayList<Publication> getPublications() {
		return publications;
	}

	public void setPublications(ArrayList<Publication> publications) {
		this.publications = publications;
	}
	
	public void updateInventory() {
		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = "";
		String sql = "SELECT * FROM DOCUMENTS";
		Connection con = null;
		PreparedStatement statement;
		try {
			con = DriverManager.getConnection(url, user, password);
			statement = con.prepareStatement("USE ensf480projectone");
			statement.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs;
		ArrayList<Publication> results = new ArrayList<Publication>();
		try {
			statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
			
			while (rs.next())
				results.add(new Publication (
						rs.getInt("ibsn"), 
						rs.getString("name"), 
						rs.getString("author"), 
						rs.getInt("copies")));
		} catch (Exception e) {e.printStackTrace();}
		publications = results;
	}
}
