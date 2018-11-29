package front_end;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import back_end.Store;
import data.Account;
import data.Message;
import users.Buyer;
import users.Operator;
import users.User;
import views.BuyerView;
import views.LoginView;
import views.OperatorView;

public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	Socket socket;
	public ObjectInputStream in;
	public ObjectOutputStream out;
	
	public Client(String serverName, int portNum){
		try{
			socket = new Socket(serverName, portNum);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch(IOException e){System.err.println(e.getStackTrace());}	
	}
	
	public boolean login(Message username, Message password) throws ClassNotFoundException, IOException{
		User user = null;
		Message message = new Message("login");
		Store store = null;
		try {
			out.writeObject(message);
			out.writeObject(username);
			out.writeObject(password);
			user = (User)in.readObject();
			if(user == null)
				return false;
			store = (Store)in.readObject();
		} 
		catch (IOException e) {e.printStackTrace();}
		if(user.getType() == 'o') { //Setup Operator
			user = new Operator(user.getUsername(), user.getType());
			((Operator) user).setStore(store);
			((Operator) user).setView(new OperatorView(this, (Operator)user));
		}
		else if(user.getType() == 'b'){ //Setup buyer
			user = new Buyer(user.getUsername(), user.getType());
			((Buyer) user).setStore(store);
			message = new Message("getAccount");
			out.writeObject(message);
			out.writeObject(new Message(user.getUsername()));
			Account account = (Account)in.readObject();
			((Buyer) user).setAccount(account);
			((Buyer) user).setView(new BuyerView(this, (Buyer)user));
		}
		return true;
	}
	
	public void closeConnection(){
		try {
			socket.close();
			System.exit(1);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void main(String[] args){
		Client client = new Client("localhost", 8099);
		@SuppressWarnings("unused")
		View view = new LoginView(client);
	}
}
