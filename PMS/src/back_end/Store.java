package back_end;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Store implements Serializable{
	private static final long serialVersionUID = 1L;
	private Inventory inventory;
	private ArrayList<Promotion> promotions;
	
	public Store(Inventory inventory, ArrayList<Promotion> promotions){
		this.inventory = inventory;
		this.promotions = promotions;
	}
	
	public Publication search(String key){ //Find publication based on string
		for(int i = 0; i < inventory.getPublications().size(); i++){
			if(inventory.getPublications().get(i).getTitle().equals(key)){
				return inventory.getPublications().get(i);
			}
		}
		return null;
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	public ArrayList<Promotion> getPromotions(){
		return promotions;
	}
	
	public ArrayList<String> getPromotionInfo()
	{
		ArrayList<String> data = new ArrayList<String>();
		for(int i = 0; i < promotions.size(); i++){
			data.add("Title: " + promotions.get(i).getPublication().getTitle() + ";  " + "Author: " + promotions.get(i).getPublication().getAuthor() + ";  " + "IBSN: " + promotions.get(i).getPublication().getIBSN());
		}
		return data;
	}
	
	public void updatePromotions()
	{
		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = "";
		String sql = "SELECT * FROM DOCUMENTS WHERE PROMOTION=1";
		Connection con = null;
		PreparedStatement statement;
		try {
			con = DriverManager.getConnection(url, user, password);
			statement = con.prepareStatement("USE ensf480projectone");
			statement.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs;
		ArrayList<Promotion> results = new ArrayList<Promotion>();
		try {
			statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
			
			while (rs.next())
				results.add(new Promotion (new Publication (
								rs.getInt("ibsn"), 
								rs.getString("name"), 
								rs.getString("author"), 
								rs.getInt("copies"))));
		} catch (Exception e) {e.printStackTrace();}
		promotions = results;
	}
}
