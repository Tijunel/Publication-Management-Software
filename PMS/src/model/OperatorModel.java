package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import back_end.Publication;
import control.Control;

public class OperatorModel extends Model
{
	
	public OperatorModel(Control control) 
	{
		super(control);
	}
	
	public void addPublication(Publication publication)
	{
		sql = "INSERT INTO DOCUMENTS" + " (NAME, AUTHOR, ISBN, PATH) " + "VALUES (?, ?, ?, ?) ";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, publication.getTitle());
			statement.setString(2, publication.getAuthor());
			statement.setInt(3, publication.getIBSN());
			statement.setString(4, publication.getPath());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePublication(Publication publication)
	{
		sql = "DELETE FROM DOCUMENTS WHERE ID=?";

		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, publication.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePublication(Publication publication)
	{
		sql = "UPDATE DOCUMENTS "
				+ "SET NAME=?, "
				+ "AUTHOR=?, "
				+ "ISBN=? WHERE ID=?";
			try {
				statement = con.prepareStatement(sql);
				statement.setString(1, publication.getTitle());
				statement.setString(2, publication.getAuthor());
				statement.setInt(3, publication.getIBSN());
				statement.setInt(4, publication.getId());
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
