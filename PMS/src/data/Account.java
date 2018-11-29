package data;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	private ShoppingCart cart;
	private Subscription subscription;
	private PaymentInformation payInfo;
	
	public Account(Subscription subscription, PaymentInformation payInfo){
		cart = new ShoppingCart();
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
