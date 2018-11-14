package views;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import front_end.View;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;


public class LoginView extends View {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
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
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(204, 148, 97, 25);
		contentPane.add(btnLogin);
		
		this.setVisible(true);
	}
}
