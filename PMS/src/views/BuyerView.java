package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import buyer_pages.*;
import front_end.*;
import users.Buyer;

public class BuyerView extends View {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BuyerView(Client client, Buyer buyer) {
		
		this.client = client;
		this.user = buyer;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 400);
		contentPane = new BuyerHomePage();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setVisible(true);
	}

}
