package buyer_pages;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class PromotionsPage extends JPanel {

	private JList list;
	private JButton btnBackToHome;
	private JButton btnCheckout;
	private JButton btnAddToCart;

	/**
	 * Create the panel.
	 */
	public PromotionsPage() {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		list = new JList();
		list.setBounds(22, 98, 466, 192);
		add(list);
		
		JLabel lblPromotions = new JLabel("Promotions");
		lblPromotions.setBounds(22, 69, 77, 16);
		add(lblPromotions);
		
		btnBackToHome = new JButton("Back to Home");
		btnBackToHome.setBounds(22, 13, 117, 25);
		add(btnBackToHome);
		
		btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(391, 303, 97, 25);
		add(btnCheckout);
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(272, 303, 107, 25);
		add(btnAddToCart);
	}

}
