package model;

import back_end.Publication;
import control.Control;

public class OperatorModel extends Model{
	
	public OperatorModel(Control control) {
		super(control);
	}
	
	public void addPublication(Publication publication){
		sql = "INSERT INTO DOCUMENTS" + " (NAME, AUTHOR, IBSN) " + "VALUES (?, ?, ?) ";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, publication.getTitle());
			statement.setString(2, publication.getAuthor());
			statement.setInt(3, publication.getIBSN());
			statement.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public void removePublication(int IBSN){
		sql = "DELETE FROM DOCUMENTS WHERE IBSN=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, IBSN);
			statement.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public void updatePublication(Publication publication){
		sql = "UPDATE DOCUMENTS "
				+ "SET NAME=?, "
				+ "AUTHOR=? WHERE IBSN=?";
			try {
				statement = con.prepareStatement(sql);
				statement.setString(1, publication.getTitle());
				statement.setString(2, publication.getAuthor());
				statement.setInt(3, publication.getIBSN());
				statement.executeUpdate();
			} catch (Exception e) {e.printStackTrace();}
	}
}
