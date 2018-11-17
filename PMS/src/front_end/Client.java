package front_end;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import back_end.Store;
import control.Control;
import control.OperatorControl;
import data.Account;
import data.Message;
import users.Buyer;
import users.Operator;
import users.User;
import views.BuyerView;
import views.LoginView;
import views.OperatorView;

public class Client {
	
	Socket socket;
	public ObjectInputStream in;
	public ObjectOutputStream out;
	
	public Client(String serverName, int portNum)
	{
		try
		{
			socket = new Socket(serverName, portNum);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch(IOException e)
		{
			System.err.println(e.getStackTrace());
		}	
	}
	
	public boolean login(Message username, Message password) throws ClassNotFoundException, IOException
	{
		User user = null;
		Control control = null;
		Message message = new Message("login");
		Store store = null;
		try 
		{
			store = (Store)in.readObject();
			out.writeObject(message);
			out.writeObject(username);
			out.writeObject(password);
			user = (User)in.readObject();
		} 
		catch (IOException e) {e.printStackTrace();}
		
		if(user == null)
			return false;
		if(user.getType() == 'o') //Setup Operator
		{
			out.writeObject(user);
			user = new Operator(user.getUsername(), user.getType());
			((Operator) user).setView(new OperatorView(this));
		}
		else if(user.getType() == 'b') //Setup buyer
		{
			out.writeObject(user);
			user = new Buyer(user.getUsername(), user.getType());
			((Buyer) user).setView(new BuyerView(this));
			((Buyer) user).setStore(store);
			message = new Message("getAccount");
			Account account = (Account)in.readObject();
			((Buyer) user).setAccount(account);
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		Client client = new Client("localhost", 8099);
		View view = new LoginView(client);
	}
}
