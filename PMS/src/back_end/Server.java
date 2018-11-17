package back_end;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import control.Control;
import control.LoginControl;
import control.OperatorControl;
import data.Message;
import model.LoginModel;
import model.Model;
import users.Operator;
import users.User;

public class Server 
{
	private ExecutorService threadpool;
	private Store store;
	private ServerSocket serverSocket;
	private Socket aSocket;
	
	public Server()
	{
		try
		{
			serverSocket = new ServerSocket(8099);
			threadpool = Executors.newCachedThreadPool();
		} 
		catch (IOException e) 
		{
			System.out.println("Error creating new socket...");
			System.out.println(e.getMessage());
		}
		System.out.println("Server is running...");
	}
	void run() throws ClassNotFoundException, InterruptedException 
	{
		store = getStoreData();
		try 
		{
			while(true)
			{
				aSocket = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(aSocket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(aSocket.getOutputStream());
				Control control = new LoginControl(in, out);
				out.writeObject(store);
				threadpool.execute(control);
			}
        } 
		catch (IOException e) 
		{
            System.out.println(e.getMessage());
            threadpool.shutdown();
            try 
            {
                aSocket.close();
            } 
            catch (IOException e1) 
            {
                e1.printStackTrace();
            }
        }
	}
	
	private Store getStoreData() //Keep private
	{
		//Insert SQL Code
		return new Store(null, null);
	}
	
	private Inventory getInventory()
	{
		//Insert SQL Code -- use to create Store object
		return new Inventory(null);
	}
	
	private ArrayList<Promotion> getPromotions()
	{
		//Insert DQL Code -- use to create Store object
		return null;
	}
	
	public static void main(String [] args) throws ClassNotFoundException, InterruptedException
	{
		Server server = new Server();
		server.run();
	}
}


