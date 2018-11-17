package model;

import control.Control;
import users.*;

public class LoginModel extends Model
{
	public LoginModel(Control control)
	{
		super(control);
	}
	
	public User getLogin(String username, String password)
	{
		return new Operator("op", 'o');
	}
}
