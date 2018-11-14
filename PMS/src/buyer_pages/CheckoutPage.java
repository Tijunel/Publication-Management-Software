package buyer_pages;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckoutPage extends JPanel {

	private JList list;
	private JButton btnConfirm;
	private JButton btnRemove;
	private JButton btnBackToHome;
	/**
	 * Create the panel.
	 */
	public CheckoutPage() {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		list = new JList();
		list.setBounds(22, 98, 466, 192);
		add(list);
		
		JLabel lblCart = new JLabel("Cart");
		lblCart.setBounds(22, 69, 56, 16);
		add(lblCart);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(391, 303, 97, 25);
		add(btnConfirm);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(282, 303, 97, 25);
		add(btnRemove);
		
		btnBackToHome = new JButton("Back to Home");
		btnBackToHome.setBounds(22, 13, 121, 25);
		add(btnBackToHome);
	}

}
