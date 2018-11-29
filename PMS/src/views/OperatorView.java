package views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import front_end.*;
import operator_pages.*;
import users.Operator;
import users.User;

public class OperatorView extends View {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public OperatorView(Client client, Operator operator) {
		this.client = client;
		this.user = operator;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 400);
		contentPane = new OperatorHome(this);
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
