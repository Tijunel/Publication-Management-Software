package operator_pages;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class OperatorHome extends JPanel {
	
	JButton btnAddDocument, btnRemoveDocument, btnUpdateDocument, btnLogOut;
	JList publicationList;

	/**
	 * Create the panel.
	 */
	public OperatorHome() {
		setLayout(null);
		
		setBounds(100, 100, 500, 400);
		
		btnAddDocument = new JButton("Add Document");
		btnAddDocument.setBounds(12, 13, 149, 25);
		add(btnAddDocument);
		
		publicationList = new JList();
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
		
		this.setVisible(true);
	}
}
