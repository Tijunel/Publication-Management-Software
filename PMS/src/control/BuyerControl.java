package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import back_end.Publication;
import data.Account;
import data.Message;
import data.PaymentInformation;
import data.ShoppingCart;
import data.Subscription;
import model.BuyerModel;
import users.Buyer;

public class BuyerControl extends Control
{
	public BuyerControl(ObjectInputStream in, ObjectOutputStream out) 
	{
		super(in, out);
	}
	
	public Publication search(String search)
	{

		return ((BuyerModel) model).search(search);
	}
	
	public void addToCart(Publication publication)
	{
		((BuyerModel) model).addToCart(publication);
	}
	
	public void checkOut(ShoppingCart cart)
	{
		((BuyerModel) model).checkOut(cart);
	}
	
	public Subscription subscribe()
	{
		return ((BuyerModel) model).subscribe();
	}
	
	public Subscription unSubscribe()
	{
		return ((BuyerModel) model).unSubscribe();
	}
	
	public void addPaymentInfo(PaymentInformation payInfo)
	{
		((BuyerModel) model).addPaymentInfo(payInfo);
	}
	
	private Account getAccount(Buyer buyer) 
	{
		return ((BuyerModel) model).getAccount(buyer);
	}
	
	public void run()
	{
		Message message = null;
		
		model = new BuyerModel(this);
		
		while(true)
		{
			try
			{
				message = (Message)in.readObject();
				
				if(message.getMessage().equals("search"))
				{
					message = (Message)in.readObject();
					out.writeObject(search(message.getMessage()));
					out.flush();
				}
				else if(message.getMessage().equals("addToCart"))
				{
					Publication add = (Publication)in.readObject();
					addToCart(add);
				}
				else if(message.getMessage().equals("checkOut"))
				{
					ShoppingCart checkOut = (ShoppingCart)in.readObject();
					checkOut(checkOut);
				}
				else if(message.getMessage().equals("subscribe"))
				{
					out.writeObject(subscribe());
					out.flush();
				}
				else if(message.getMessage().equals("unsubscribe"))
				{
					out.writeObject(unSubscribe());
					out.flush();
				}
				else if(message.getMessage().equals("addPay"))
				{
					PaymentInformation payInfo = (PaymentInformation)in.readObject();
					addPaymentInfo(payInfo);
				}
				else if(message.getMessage().equals("getAccount"))
				{
					Buyer buyer = (Buyer)in.readObject();
					out.writeObject(getAccount(buyer));
				}
			}
			catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
