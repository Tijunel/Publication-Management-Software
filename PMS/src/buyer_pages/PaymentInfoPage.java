package buyer_pages;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentInfoPage extends JPanel {
	private JTextField txtCardnumber;
	private JTextField txtVerificationcode;
	private JButton btnBack, btnSave;
	/**
	 * Create the panel.
	 */
	public PaymentInfoPage() {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(22, 13, 97, 25);
		add(btnBack);
		
		txtCardnumber = new JTextField();
		txtCardnumber.setText("");
		txtCardnumber.setBounds(161, 73, 116, 22);
		add(txtCardnumber);
		txtCardnumber.setColumns(10);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setBounds(22, 76, 83, 16);
		add(lblCardNumber);
		
		JLabel lblVerificationCode = new JLabel("Verification Code");
		lblVerificationCode.setBounds(22, 105, 116, 16);
		add(lblVerificationCode);
		
		txtVerificationcode = new JTextField();
		txtVerificationcode.setText("");
		txtVerificationcode.setBounds(161, 102, 116, 22);
		add(txtVerificationcode);
		txtVerificationcode.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(391, 137, 97, 25);
		add(btnSave);
		
		this.setVisible(true);
	}

}
