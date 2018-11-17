package data;

public class Account 
{
	private ShoppingCart cart;
	private Subscription subscription;
	private PaymentInformation payInfo;
	
	public Account(ShoppingCart cart, Subscription subscription, PaymentInformation payInfo)
	{
		this.cart = cart;
		this.subscription = subscription;
		this.payInfo = payInfo;
	}
}
