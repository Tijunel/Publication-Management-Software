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
	public BuyerModel(Control control) 
	{
		super(control);
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
		//Insert SQL Code
	}
	
	public void checkOut(ShoppingCart cart)
	{
		//Insert SQL Code
	}
	
	public Subscription subscribe()
	{
		//Insert SQL Code
		return new Subscription(true);
	}
	
	public Subscription unSubscribe()
	{
		//Insert SQL Code
		return new Subscription(false);
	}
	
	public void addPaymentInfo(PaymentInformation payInfo)
	{
		//Insert SQL Code
	}

	public Account getAccount(Buyer buyer) 
	{
		//Insert SQL Code
		return new Account(null, null, null);
	}
}
