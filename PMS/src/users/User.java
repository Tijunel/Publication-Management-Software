package users;

import java.io.Serializable;
import front_end.View;

public abstract class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected String username;
	protected View view;
	protected String email;
	protected char type;
	
	public User(String username, char type)
	{
		this.username = username;
		this.type = type;
	}
	
	public char getType()
	{
		return type;
	}
	
	public String getUsername()
	{
		return username;
	}
}
