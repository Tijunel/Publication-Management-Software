package data;

public class Account 
{
	private ShoppingCart cart;
	private Subscription subscription;
	private PaymentInformation payInfo;
	
	public Account(ShoppingCart cart, Subscription subscription, PaymentInformation payInfo)
	{
		this.setCart(cart);
		this.setSubscription(subscription);
		this.setPayInfo(payInfo);
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public PaymentInformation getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(PaymentInformation payInfo) {
		this.payInfo = payInfo;
	}
}
