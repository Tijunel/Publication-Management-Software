package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import data.Account;
import data.Message;
import data.PaymentInformation;
import data.ShoppingCart;
import data.Subscription;
import model.BuyerModel;

public class BuyerControl extends Control
{
	public BuyerControl(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}
	
	public void checkOut(ShoppingCart cart){
		((BuyerModel) model).checkOut(cart);
	}
	
	public Subscription subscribe(String s){
		return ((BuyerModel) model).subscribe(s);
	}
	
	public Subscription unSubscribe(String s){
		return ((BuyerModel) model).unSubscribe(s);
	}
	
	public void addPaymentInfo(String username, PaymentInformation payInfo){
		((BuyerModel) model).addPaymentInfo(username, payInfo);
	}
	
	private Account getAccount(String username) {
		return ((BuyerModel) model).getAccount(username);
	}
	
	public void run(){
		Message message = null;
		model = new BuyerModel(this);
		while(true){
			try{
				message = (Message)in.readObject();
				
//				if(message.getMessage().equals("checkOut")){
//					ShoppingCart checkOut = (ShoppingCart)in.readObject();
//					checkOut(checkOut);
//				}
				if(message.getMessage().equals("subscribe")){
					Message m = (Message)in.readObject();
					out.writeObject(subscribe(m.getMessage()));
					out.flush();
				}else if(message.getMessage().equals("unsubscribe")){
					Message m = (Message)in.readObject();
					out.writeObject(unSubscribe(m.getMessage()));
					out.flush();
				}else if(message.getMessage().equals("addPay")){
					PaymentInformation payInfo = (PaymentInformation)in.readObject();
					Message m = (Message)in.readObject();
					addPaymentInfo(m.getMessage(), payInfo);
				}else if(message.getMessage().equals("getAccount")){
					Message username = (Message)in.readObject();
					out.writeObject(getAccount(username.getMessage()));
				}else if(message.getMessage().equals("close")){
					return;
				}
			}catch (ClassNotFoundException | IOException e) {e.printStackTrace();}
		}
	}
}
