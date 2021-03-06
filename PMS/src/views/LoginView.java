package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import data.Message;
import front_end.Client;
import front_end.View;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;

public class LoginView extends View {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnLogin;

	public LoginView(Client client) {
		
		this.client = client;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(121, 72, 70, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(121, 101, 70, 16);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setText("");
		txtUsername.setBounds(185, 69, 116, 22);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("");
		txtPassword.setBounds(185, 101, 116, 22);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblPms = new JLabel("PMS");
		lblPms.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPms.setBounds(183, 13, 56, 16);
		contentPane.add(lblPms);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(204, 148, 97, 25);
		contentPane.add(btnLogin);
		
		addListeners();
		this.setVisible(true);
	}
	
	private void addListeners() { 
		Listener l = new Listener();
		btnLogin.addActionListener(l);
	}
	
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLogin){
				try {
					boolean loggedIn = client.login(new Message(txtUsername.getText()), new Message(txtPassword.getText()));
					if(loggedIn){
						contentPane.setVisible(false);
						setVisible(false);
						dispose();
					}
					else{
						JOptionPane optionPane = new JOptionPane("Email or password was entered incorrectly.", JOptionPane.ERROR_MESSAGE);    
						JDialog dialog = optionPane.createDialog("Email or password was entered incorrectly.");
						dialog.setVisible(true);
						txtUsername.setText("");
						txtPassword.setText("");
					}
				} catch (ClassNotFoundException | IOException e1) {e1.printStackTrace();}
				return;
			}
		}
	}
}
