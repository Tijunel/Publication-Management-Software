package operator_pages;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;

public class OperatorHome extends JPanel {
	
	JList publicationList;

	/**
	 * Create the panel.
	 */
	public OperatorHome() {
		setLayout(null);
		
		setBounds(100, 100, 500, 400);
		
		JButton btnAddDocument = new JButton("Add Document");
		btnAddDocument.setBounds(12, 13, 149, 25);
		add(btnAddDocument);
		
		JList publicationList = new JList();
		publicationList.setBounds(12, 51, 476, 199);
		add(publicationList);
		
		JButton btnRemoveDocument = new JButton("Remove Document");
		btnRemoveDocument.setBounds(173, 13, 164, 25);
		add(btnRemoveDocument);
		
		JButton btnUpdateDocument = new JButton("Update Document");
		btnUpdateDocument.setBounds(349, 13, 139, 25);
		add(btnUpdateDocument);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(391, 263, 97, 25);
		add(btnLogOut);
		
		this.setVisible(true);
	}
}
