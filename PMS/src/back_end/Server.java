package back_end;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import control.Control;
import control.LoginControl;

public class Server {
	private ExecutorService threadpool;
	private Store store;
	private ServerSocket serverSocket;
	private Socket aSocket;
	String url = "jdbc:mysql://localhost:3306";
	String user = "root";
	String password = "";
	String sql;
	Connection con;
	PreparedStatement statement;
	ResultSet rs;
	
	public Server(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			statement = con.prepareStatement("USE ensf480projectone");
			statement.execute();
		} catch (Exception e) {e.printStackTrace();}
		
		try{
			serverSocket = new ServerSocket(8099);
			threadpool = Executors.newCachedThreadPool();
		} 
		catch (IOException e) {
			System.out.println("Error creating new socket...");
			System.out.println(e.getMessage());
		}
		System.out.println("Server is running...");
	}
	void run() throws ClassNotFoundException, InterruptedException, SocketException {
		store = getStoreData();
		try {
			while(true){
				aSocket = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(aSocket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(aSocket.getOutputStream());
				Control control = new LoginControl(in, out, store);
				threadpool.execute(control);
			}
        } 
		catch (IOException e) {
            System.out.println(e.getMessage());
            threadpool.shutdown();
            try {
                aSocket.close();
                return;
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
	}
	
	private Store getStoreData() {
		Inventory inv = getInventory();
		ArrayList <Promotion> promotions = getPromotions();
		return new Store(inv, promotions);
	}
	
	private Inventory getInventory(){
		sql = "SELECT * FROM DOCUMENTS";
		ArrayList<Publication> results = new ArrayList<Publication>();
		try {
			statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
			
			while (rs.next())
				results.add(new Publication (
						rs.getInt("ibsn"), 
						rs.getString("name"), 
						rs.getString("author"), 
						rs.getInt("copies")));
		} catch (Exception e) {e.printStackTrace();}
		return new Inventory(results);
	}
	
	private ArrayList<Promotion> getPromotions(){
		sql = "SELECT * FROM DOCUMENTS WHERE PROMOTION=1";
		ArrayList<Promotion> results = new ArrayList<Promotion>();
		try {
			statement = con.prepareStatement(sql);
			rs = statement.executeQuery();
			
			while (rs.next()) {
				results.add(new Promotion (new Publication (
						rs.getInt("ibsn"), 
						rs.getString("name"), 
						rs.getString("author"), 
						rs.getInt("copies"))));
			}
		} catch (Exception e) {e.printStackTrace();}
		return results;
	}
	
	public static void main(String [] args) throws ClassNotFoundException, InterruptedException, SocketException{
		Server server = new Server();
		server.run();
	}
}


