package users;

import back_end.Store;
import data.Account;
import front_end.View;

public class Buyer extends User{
	private static final long serialVersionUID = 1L;
	Store store;
	private Account account;
	
	public Buyer(String username, char type) {
		super(username, type);
	}
	
	public Buyer(String username, Account account) {
		super(username, 'b');
		this.account = account;
	}
	
	public void setView(View view){
		this.view = view;
	}
	
	public void setAccount(Account account){
		this.account = account;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public boolean subscribed()
	{
		if(account.getSubscription().isValid())
			return true;
		else
			return false;
	}
}
 