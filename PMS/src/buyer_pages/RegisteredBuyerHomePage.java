package buyer_pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import back_end.Publication;
import data.Message;
import users.Buyer;
import views.BuyerView;

public class RegisteredBuyerHomePage extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnLogOut, btnUnRegister, btnCheckout, btnAddToCart, btnAddPaymentInfo;
	@SuppressWarnings("rawtypes")
	private JList list;
	private ArrayList<Publication> pubList;
	private JButton btnPromotions;
	private BuyerView frame;
	private static boolean newPromotions = false;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegisteredBuyerHomePage(BuyerView frame) {
		this.frame = frame;
		setLayout(null);
		setBounds(100, 100, 525, 400);
		btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(391, 13, 97, 25);
		add(btnLogOut);
		JLabel lblPublicationList = new JLabel("Publication List");
		lblPublicationList.setBounds(22, 69, 97, 16);
		add(lblPublicationList);
		frame.getUser().getStore().getInventory().updateInventory();
		pubList = frame.getUser().getStore().getInventory().getPublications();
		list = new JList(frame.getUser().getPublicationInfo().toArray());
		list.setBounds(22, 98, 466, 192);
		add(list);
		if(frame.getUser().getStore().getPromotionInfo().size() > 0 && !newPromotions){//Notify users when new promotions exist
			JOptionPane.showMessageDialog(frame,"There are new promotions");
			newPromotions = true;
		}
		btnUnRegister = new JButton("UnRegister");
		btnUnRegister.setBounds(22, 13, 97, 25);
		add(btnUnRegister);
		btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(391, 303, 97, 25);
		add(btnCheckout);
		btnAddToCart = new JButton("Add To Cart");
		btnAddToCart.setBounds(272, 303, 107, 25);
		add(btnAddToCart);
		btnAddPaymentInfo = new JButton("Add Payment Info");
		btnAddPaymentInfo.setBounds(131, 13, 140, 25);
		add(btnAddPaymentInfo);
		btnPromotions = new JButton("Promotions");
		btnPromotions.setBounds(22, 303, 107, 25);
		add(btnPromotions);
		Listener l = new Listener(this);
		btnLogOut.addActionListener(l);
		btnUnRegister.addActionListener(l);
		btnCheckout.addActionListener(l);
		btnAddToCart.addActionListener(l);
		btnAddPaymentInfo.addActionListener(l);
		btnPromotions.addActionListener(l);
		this.setVisible(true);
	}
	
	class Listener implements ActionListener{
		JPanel panel;
		Listener(JPanel panel){
			this.panel = panel;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnLogOut) {
				setVisible(false);
				try {
					frame.getClient().out.writeObject(new Message("close"));
				} catch (IOException e1) {e1.printStackTrace();}
				frame.setVisible(false);
				frame.getClient().closeConnection();
			}else if(e.getSource() == btnUnRegister) {
				try {
					frame.getClient().out.writeObject(new Message("unsubscribe"));
					frame.getClient().out.writeObject(new Message(frame.getUser().getUsername()));
				} catch (IOException e1) {e1.printStackTrace();}
				JOptionPane.showMessageDialog(frame,"Unsubscribed");
				panel.setVisible(false);
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
					JOptionPane.showMessageDialog(frame,"Select a publication, then click add to cart.");
					return;
				}
				Publication selected = pubList.get(index);
				((Buyer) frame.getUser()).getAccount().getCart().addToCart(selected);
				JOptionPane.showMessageDialog(frame, selected.getTitle() + " has been added to your cart.");
			}else if(e.getSource() == btnAddPaymentInfo) {
				panel.setVisible(false);
				panel = new PaymentInfoPage(frame);
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}else if(e.getSource() == btnPromotions){
				panel.setVisible(false);
				panel = new PromotionsPage(frame);
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}
		}
	}
}
