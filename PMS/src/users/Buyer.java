package users;

import back_end.Store;
import data.Account;
import front_end.View;

public class Buyer extends User
{
	Store store;
	private Account account;
	
	public Buyer(String username, char type) {
		super(username, type);
	}
	
	public void setView(View view)
	{
		this.view = view;
	}
	
	public void setAccount(Account account)
	{
		this.account = account;
	}
	
	public void setStore(Store store)
	{
		this.store = store;
	}
}
 