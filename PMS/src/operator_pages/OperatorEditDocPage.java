package operator_pages;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class OperatorEditDocPage extends JPanel {
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtNumber;
	private JButton btnCancel;
	private JButton btnSubmit;
	
	public OperatorEditDocPage() {
		setLayout(null);
		setBounds(100, 100, 500, 400);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(15, 17, 69, 20);
		add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(15, 53, 69, 20);
		add(lblAuthor);
		
		JLabel lblIbsn = new JLabel("IBSN");
		lblIbsn.setBounds(15, 89, 69, 20);
		add(lblIbsn);
		
		txtTitle = new JTextField();
		txtTitle.setText("");
		txtTitle.setBounds(99, 14, 146, 26);
		add(txtTitle);
		txtTitle.setColumns(10);
		
		txtAuthor = new JTextField();
		txtAuthor.setText("");
		txtAuthor.setBounds(99, 50, 146, 26);
		add(txtAuthor);
		txtAuthor.setColumns(10);
		
		txtNumber = new JTextField();
		txtNumber.setText("");
		txtNumber.setBounds(99, 89, 146, 26);
		add(txtNumber);
		txtNumber.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(15, 199, 115, 29);
		add(btnCancel);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(370, 199, 115, 29);
		add(btnSubmit);
		
		
	}
}
