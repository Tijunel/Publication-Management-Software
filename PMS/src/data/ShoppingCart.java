package data;

import java.io.Serializable;
import java.util.ArrayList;
import back_end.Publication;

public class ShoppingCart implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Publication> cart;
	
	public ShoppingCart(){
		cart = new ArrayList<Publication>();
	}

	public ArrayList<Publication> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Publication> cart) {
		this.cart = cart;
	}
	
	public void addToCart(Publication pub) {
		cart.add(pub);
	}
	
	public void removeFromCart(Publication pub) {
		cart.remove(pub);
	}
	
	public void clearCart(){
		cart = new ArrayList<Publication>();
	}
	
	public ArrayList<String> getCartInfo(){
		ArrayList<String> data = new ArrayList<String>();
		for(int i = 0; i < cart.size(); i++){
			data.add("Title: " + cart.get(i).getTitle() + ";  " + "Author: " + cart.get(i).getAuthor() + ";  " + "IBSN: " + cart.get(i).getIBSN());
		}
		return data;
	}
}
