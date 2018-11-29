package buyer_pages;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import back_end.Promotion;
import back_end.Publication;
import users.Buyer;
import views.BuyerView;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class PromotionsPage extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private JList list;
	private ArrayList<Promotion> promotedPublications;
	private JButton btnBackToHome;
	private JButton btnCheckout;
	private JButton btnAddToCart;
	private BuyerView frame;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PromotionsPage(BuyerView frame) {
		this.frame = frame;
		setLayout(null);
		setBounds(100, 100, 500, 400);
		frame.getUser().getStore().updatePromotions();
		promotedPublications = frame.getUser().getStore().getPromotions();
		list = new JList(frame.getUser().getStore().getPromotionInfo().toArray());//Get promotion info
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
		Listener l = new Listener(this);
		btnBackToHome.addActionListener(l);
		btnCheckout.addActionListener(l);
		btnAddToCart.addActionListener(l);
		this.setVisible(true);
	}
	
	class Listener implements ActionListener{
		JPanel panel;
		Listener(JPanel panel){
			this.panel = panel;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnBackToHome) {
				panel.setVisible(false);
				if(((Buyer) frame.getUser()).subscribed())
					panel = new RegisteredBuyerHomePage(frame);
				else
					panel = new BuyerHomePage(frame);
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}else if(e.getSource() == btnCheckout) {
				panel.setVisible(false);
				panel = new CheckoutPage(frame);
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}else if(e.getSource() == btnAddToCart) {
				int index = list.getSelectedIndex();
				if(index == -1) {
					JOptionPane.showMessageDialog(frame,"Select a promotion, then click add to cart.");
					return;
				}
				Publication selected = promotedPublications.get(index).getPublication();
				((Buyer) frame.getUser()).getAccount().getCart().addToCart(selected);
				JOptionPane.showMessageDialog(frame, selected.getTitle() + " has been added to your cart.");
			}
		}
	}
}
