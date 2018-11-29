package model;

import control.Control;
import data.Account;
import data.PaymentInformation;
import data.ShoppingCart;
import data.Subscription;

public class BuyerModel extends Model{
	
	public BuyerModel(Control control) {
		super(control);
	}
	
	public void checkOut(ShoppingCart cart) { //this only updates the database, we will need to update the store as well when this message is sent
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
	
	public Subscription subscribe(String username){
		sql = "UPDATE BUYER SET SUBSCRIPTION=1 WHERE USERNAME=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Subscription(true);
	}
	
	public Subscription unSubscribe(String username){
		sql = "UPDATE BUYER SET SUBSCRIPTION=0 WHERE USERNAME=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Subscription(false);
	}
	
	public void addPaymentInfo(String username, PaymentInformation payInfo){
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

	public Account getAccount(String username) { //pulls account from database{
		sql = "SELECT * FROM BUYER WHERE USERNAME=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			rs = statement.executeQuery();
			
			if (rs.next()) {
				return new Account(new Subscription(rs.getBoolean("subscription")), 
								   new PaymentInformation(rs.getString("type"), rs.getInt("payment")));
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
}
