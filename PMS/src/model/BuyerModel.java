package model;

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
	
	public Publication search(String search)
	{
		//Insert SQL Code
		return new Publication(1, "book", "me");
	}
	
	public void addToCart(Publication publication)
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
