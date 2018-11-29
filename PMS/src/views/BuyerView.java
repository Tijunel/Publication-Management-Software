package views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import buyer_pages.*;
import front_end.*;
import users.Buyer;
import users.User;

public class BuyerView extends View {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public BuyerView(Client client, Buyer buyer) {
		this.client = client;
		this.user = buyer;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 400);
		if(((Buyer) user).subscribed())
			contentPane = new RegisteredBuyerHomePage(this);
		else
			contentPane = new BuyerHomePage(this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
	}
	
	public Client getClient() {//Needed for closing connection properly.
		return client;
	}
	
	public User getUser() {
		return user;
	}
}
