package data;

import java.util.ArrayList;
import back_end.Publication;

public class ShoppingCart 
{
	private ArrayList<Publication> cart;
	
	public ShoppingCart(ArrayList<Publication> items)
	{
		this.setCart(items);
	}

	public ArrayList<Publication> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Publication> cart) {
		this.cart = cart;
	}
}
