package buyer_pages;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import data.Message;
import data.PaymentInformation;
import users.Buyer;
import views.BuyerView;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class PaymentInfoPage extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtCardnumber;
	private JTextField txtCardType;
	private JButton btnBack, btnSave;
	private BuyerView frame;

	public PaymentInfoPage(BuyerView frame) {
		this.frame = frame;
		setLayout(null);
		setBounds(100, 100, 500, 400);
		btnBack = new JButton("Back");
		btnBack.setBounds(22, 13, 97, 25);
		add(btnBack);
		txtCardnumber = new JTextField();
		txtCardnumber.setText(Integer.toString(((Buyer) frame.getUser()).getAccount().getPayInfo().getCardNumber()));
		txtCardnumber.setBounds(161, 73, 116, 22);
		add(txtCardnumber);
		txtCardnumber.setColumns(10);
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setBounds(22, 76, 83, 16);
		add(lblCardNumber);
		txtCardType = new JTextField();
		txtCardType.setText(((Buyer) frame.getUser()).getAccount().getPayInfo().getCardType());
		txtCardType.setBounds(161, 102, 116, 22);
		add(txtCardType);
		txtCardType.setColumns(10);
		JLabel lblCardType = new JLabel("Card Type");
		lblCardType.setBounds(22, 105, 116, 16);
		add(lblCardType);
		btnSave = new JButton("Save");
		btnSave.setBounds(391, 137, 97, 25);
		add(btnSave);
		Listener l = new Listener(this);
		btnBack.addActionListener(l);
		btnSave.addActionListener(l);
		this.setVisible(true);
	}

	class Listener implements ActionListener{
		JPanel panel;
		Listener(JPanel panel){
			this.panel = panel;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnBack) {
				panel.setVisible(false);
				if(((Buyer) frame.getUser()).subscribed())
					panel = new RegisteredBuyerHomePage(frame);
				else
					panel = new BuyerHomePage(frame);
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}else if(e.getSource() == btnSave) {//Edit to send user info to edit correct info
				try {
					frame.getClient().out.writeObject(new Message("addPay"));
					frame.getClient().out.writeObject(new PaymentInformation(txtCardType.getText(), Integer.parseInt(txtCardnumber.getText())));
					frame.getClient().out.writeObject(new Message(frame.getUser().getUsername()));
					((Buyer) frame.getUser()).getAccount().getPayInfo().setCardNumber(Integer.parseInt(txtCardnumber.getText()));
					((Buyer) frame.getUser()).getAccount().getPayInfo().setCardType(txtCardType.getText());
				} catch (IOException e1) {e1.printStackTrace();}
				JOptionPane.showMessageDialog(frame, "Your payment information has been saved.");
			}
		}
	}
}
