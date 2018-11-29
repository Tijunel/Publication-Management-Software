package users;

import front_end.View;

public class Operator extends User{
	private static final long serialVersionUID = 1L;

	public Operator(String username, char type) {
		super(username, type);
	}
	
	public void setView(View view){
		this.view = view;
	}
}
