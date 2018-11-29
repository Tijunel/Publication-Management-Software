package control;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import back_end.Publication;
import data.Message;
import model.OperatorModel;
 
public class OperatorControl extends Control
{
	public OperatorControl(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}
	
	public void addPublication(Publication publication){
		((OperatorModel) model).addPublication(publication);
	}
	
	public void removePublication(int IBSN){
		((OperatorModel) model).removePublication(IBSN);
	}
	
	public void updatePublication(Publication publication){
		((OperatorModel) model).updatePublication(publication);
	}

	public void run() {
		Message message = null;
		model = new OperatorModel(this);
		while (true) {
			try {
				message = (Message)in.readObject();
				
				if(message.getMessage().equals("add")){
					Publication add = (Publication)in.readObject();
					addPublication(add);
				}else if(message.getMessage().equals("remove")){
					Message IBSN = (Message)in.readObject();
					removePublication(Integer.parseInt(IBSN.getMessage()));
				}else if(message.getMessage().equals("update")){
					Publication update = (Publication)in.readObject();
					updatePublication(update);
				}else if(message.getMessage().equals("close")){
					return;
				}
			} catch (ClassNotFoundException | IOException e) {e.printStackTrace(); return;}
		}
	}
}
