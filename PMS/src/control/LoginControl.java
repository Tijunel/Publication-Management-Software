package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import back_end.Store;
import data.Message;
import model.LoginModel;
import users.User;

public class LoginControl extends Control{
	Store store;
	public LoginControl(ObjectInputStream in, ObjectOutputStream out, Store store) {
		super(in, out);
		this.store = store;
	}

	public void run() {
		Message message = null;
		while (true) {
			try {
				message = (Message) in.readObject();
				if (message.getMessage().equals("login")) {
					Message username = (Message) in.readObject();
					Message password = (Message) in.readObject();
					model = new LoginModel(this); 
					User user = ((LoginModel)model).getLogin(username.getMessage(), password.getMessage());
					out.writeObject(user);
					Control control = null;
					if(user != null){
						out.writeObject(store);
						if(user.getType() == 'o'){ //Start Operator Control
							control = new OperatorControl(in, out);
							control.run();
						}else if(user.getType() == 'b'){ //Start Buyer Control
							control = new BuyerControl(in, out);
							control.run();
						}
					}
					return;
				}
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
				return;
			}
		}
	}
}
