package operator_pages;

import javax.swing.JPanel;
import data.Message;
import views.OperatorView;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class OperatorHome extends JPanel {
	private static final long serialVersionUID = 1L;
	JButton btnAddDocument, btnRemoveDocument, btnUpdateDocument, btnLogOut;
	@SuppressWarnings("rawtypes")
	JList publicationList;
	OperatorView frame;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OperatorHome(OperatorView frame) {
		this.frame = frame;
		setLayout(null);
		setBounds(100, 100, 500, 400);
		btnAddDocument = new JButton("Add Document");
		btnAddDocument.setBounds(12, 13, 149, 25);
		add(btnAddDocument);
		frame.getUser().getStore().getInventory().updateInventory();
		publicationList = new JList(frame.getUser().getPublicationInfo().toArray());
		publicationList.setBounds(12, 80, 476, 199);
		add(publicationList);
		btnRemoveDocument = new JButton("Remove Document");
		btnRemoveDocument.setBounds(173, 13, 164, 25);
		add(btnRemoveDocument);
		btnUpdateDocument = new JButton("Update Document");
		btnUpdateDocument.setBounds(349, 13, 139, 25);
		add(btnUpdateDocument);
		btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(391, 292, 97, 25);
		add(btnLogOut);
		JLabel lblPublicationsList = new JLabel("Publications List");
		lblPublicationsList.setBounds(22, 51, 102, 16);
		add(lblPublicationsList);
		//Add in listeners
		Listener l = new Listener(this);
		btnAddDocument.addActionListener(l);
		btnRemoveDocument.addActionListener(l);
		btnUpdateDocument.addActionListener(l);
		btnLogOut.addActionListener(l);
		this.setVisible(true);
	}
	
	class Listener implements ActionListener{
		JPanel panel;
		Listener(JPanel panel){
			this.panel = panel;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnAddDocument) {//Goto add doc page
				panel.setVisible(false);
				panel = new OperatorEditDocPage(frame, 'a');
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}
			else if(e.getSource() == btnRemoveDocument){//Goto remove doc page
				panel.setVisible(false);
				panel = new OperatorEditDocPage(frame, 'r');
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}
			else if(e.getSource() == btnUpdateDocument) {//Goto update doc page
				panel.setVisible(false);
				panel = new OperatorEditDocPage(frame, 'u');
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}
			else if(e.getSource() == btnLogOut) { //Turn everything off
				setVisible(false);
				try {
					frame.getClient().out.writeObject(new Message("close"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false);
				frame.getClient().closeConnection();
			}
		}
	}
}
