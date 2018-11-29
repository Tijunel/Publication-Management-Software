package buyer_pages;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import back_end.Publication;
import users.Buyer;
import views.BuyerView;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CheckoutPage extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private JList list;
	private ArrayList<Publication> cart;
	private JButton btnConfirm;
	private JButton btnRemove;
	private JButton btnBackToHome;
	private BuyerView frame;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CheckoutPage(BuyerView frame) {
		this.frame = frame;
		setLayout(null);
		setBounds(100, 100, 500, 400);
		cart = ((Buyer) frame.getUser()).getAccount().getCart().getCart();
		list = new JList(((Buyer) frame.getUser()).getAccount().getCart().getCartInfo().toArray());
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
		Listener l = new Listener(this);
		btnConfirm.addActionListener(l);
		btnRemove.addActionListener(l);
		btnBackToHome.addActionListener(l);
		this.setVisible(true);
	}

	class Listener implements ActionListener
	{
		JPanel panel;
		Listener(JPanel panel){
			this.panel = panel;
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnConfirm) {
				if(cart.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"There is nothing in your cart yet!");
					return;
				}
				//try {//Not sure how we would even do anything considering there is no data to actually update
					//frame.getClient().out.writeObject(new Message("checkOut"));
					JOptionPane.showMessageDialog(frame,"Your order has been placed and your cart has been cleared! Thank you for shopping with us.");
					//Clear cart since you made an order
					cart = new ArrayList<Publication>();
					list = new JList();
					((Buyer) frame.getUser()).getAccount().getCart().clearCart();
				//} catch (IOException e1) {e1.printStackTrace();}
			}else if(e.getSource() == btnRemove) {
				int index = list.getSelectedIndex();
				if(index == -1) {
					JOptionPane.showMessageDialog(frame,"Select a publication in your cart, then click remove.");
					return;
				}
				Publication selected = cart.get(index);
				((Buyer) frame.getUser()).getAccount().getCart().removeFromCart(selected);
				cart = ((Buyer) frame.getUser()).getAccount().getCart().getCart();//Update
				list = new JList(((Buyer) frame.getUser()).getAccount().getCart().getCartInfo().toArray());
				JOptionPane.showMessageDialog(frame,selected.getTitle() + " has been removed from your cart.");
				frame.repaint();
			}else if(e.getSource() == btnBackToHome) {
				panel.setVisible(false);
				if(((Buyer) frame.getUser()).subscribed())
					panel = new RegisteredBuyerHomePage(frame);
				else
					panel = new BuyerHomePage(frame);
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}
		}
	}
}
