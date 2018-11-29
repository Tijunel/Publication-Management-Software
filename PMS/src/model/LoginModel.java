package model;

import control.Control;
import data.Account;
import data.PaymentInformation;
import data.Subscription;
import users.*;

public class LoginModel extends Model {
	public LoginModel(Control control) {
		super(control);
	}

	public User getLogin(String username, String password) {
		sql = "SELECT * FROM STAFF WHERE USERNAME=? AND PASSWORD=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			rs = statement.executeQuery();

			if (rs.next())
			{
				String t = rs.getString("type");
				char t2 = t.charAt(0);
				if (t.equals("o"))
				{
					User newUser = new Operator(rs.getString("username"), t2);
					return newUser;
				}
				else if (t.equals("m"))
				{
					//User newUser = new (rs.getString("username"), t2);
					return null;
				}
			}
			
		} catch (Exception e) {e.printStackTrace();}
		
		//looking in buyer table
		sql = "SELECT * FROM BUYER WHERE USERNAME=? AND PASSWORD=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			rs = statement.executeQuery();

			if (rs.next())
			{
				PaymentInformation pi = new PaymentInformation(rs.getString("type"), rs.getInt("payment"));
				Subscription s = new Subscription(rs.getBoolean("subscription"));
				Account a = new Account(s, pi);
				User newUser = new Buyer(rs.getString("username"), a);
				return newUser;
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
}
