package buyer_pages;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

public class BuyerHomePage extends JPanel {

	private JButton btnLogOut, btnRegister, btnCheckout, btnAddToCart, btnAddPaymentInfo;
	private JList list;
	
	/**
	 * Create the panel.
	 */
	public BuyerHomePage() {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(391, 13, 97, 25);
		add(btnLogOut);
		
		JLabel lblPublicationList = new JLabel("Publication List");
		lblPublicationList.setBounds(22, 69, 97, 16);
		add(lblPublicationList);
		
		list = new JList();
		list.setBounds(22, 98, 466, 192);
		add(list);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(22, 13, 97, 25);
		add(btnRegister);
		
		btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(391, 303, 97, 25);
		add(btnCheckout);
		
		btnAddToCart = new JButton("Add To Cart");
		btnAddToCart.setBounds(272, 303, 107, 25);
		add(btnAddToCart);
		
		btnAddPaymentInfo = new JButton("Add Payment Info");
		btnAddPaymentInfo.setBounds(131, 13, 140, 25);
		add(btnAddPaymentInfo);
		
		this.setVisible(true);
	}

}
