package model;

import java.util.ArrayList;

import back_end.Publication;
import control.Control;
import data.Account;
import data.PaymentInformation;
import data.ShoppingCart;
import data.Subscription;
import users.Buyer;

public class BuyerModel extends Model
{
	Account account;
	
	public BuyerModel(Control control) 
	{
		super(control);
		//make account here
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
	
	public void addToCart(Publication publication) //Does not require sql code
	{
		//move this over to the client side
		//Insert SQL Code
	}
	
	public void checkOut(ShoppingCart cart) //this only updates the database, we will need to update the store as well when this message is sent
	{
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
	
	public Subscription subscribe(String username)
	{
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
	
	public Subscription unSubscribe(String username)
	{
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
	
	public void addPaymentInfo(String username, PaymentInformation payInfo)
	{
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

	public Account getAccount(Buyer buyer) //pulls account from database
	{
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
}
