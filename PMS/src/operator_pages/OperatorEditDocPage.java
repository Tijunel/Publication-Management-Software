package operator_pages;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import back_end.Publication;
import data.Message;
import views.OperatorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;

public class OperatorEditDocPage extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtNumber;
	private JButton btnCancel;
	private JButton btnSubmit;
	private OperatorView frame;
	private char type;
	
	public OperatorEditDocPage(OperatorView frame, char type) {
		this.type = type;
		this.frame = frame;
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
		Listener l = new Listener(this);
		btnCancel.addActionListener(l);
		btnSubmit.addActionListener(l);
	}
	
	class Listener implements ActionListener
	{
		JPanel panel;
		Listener(JPanel panel){
			this.panel = panel;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnCancel) {
				panel.setVisible(false);
				panel = new OperatorHome(frame);
				frame.setContentPane(panel);
				frame.setBounds(100, 100, 525, 400);
			}
			else if(e.getSource() == btnSubmit){
				//Error checking
				if((txtNumber.getText().equals("") || txtTitle.getText().equals("") || txtAuthor.getText().equals("")) && type != 'r'){
					JOptionPane optionPane = new JOptionPane("Must fill all fields.", JOptionPane.ERROR_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Must fill all fields.");
					dialog.setVisible(true);
					return;
				}
				else if(txtNumber.getText().equals("")){
					JOptionPane optionPane = new JOptionPane("Must fill IBSN field.", JOptionPane.ERROR_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Must fill IBSN field.");
					dialog.setVisible(true);
					return;
				}
				try {
					if(type == 'a') {//Add to DB
						frame.getClient().out.writeObject(new Message("add"));
						frame.getClient().out.writeObject(new Publication(Integer.parseInt(txtNumber.getText()), txtTitle.getText(), txtAuthor.getText()));
						JOptionPane.showMessageDialog(frame,"Document Added.");
						panel.setVisible(false);
						panel = new OperatorHome(frame);
						frame.setContentPane(panel);
						frame.setBounds(100, 100, 525, 400);
					}
					else if(type == 'r') { //Remove from DB
						frame.getClient().out.writeObject(new Message("remove"));
						frame.getClient().out.writeObject(new Message(txtNumber.getText()));
						JOptionPane.showMessageDialog(frame,"Document Removed.");
						panel.setVisible(false);
						panel = new OperatorHome(frame);
						frame.setContentPane(panel);
						frame.setBounds(100, 100, 525, 400);
					}
					else if(type == 'u') { //Update DB
						frame.getClient().out.writeObject(new Message("update"));
						frame.getClient().out.writeObject(new Publication(Integer.parseInt(txtNumber.getText()), txtTitle.getText(), txtAuthor.getText()));
						JOptionPane.showMessageDialog(frame,"Document Updated.");
						panel.setVisible(false);
						panel = new OperatorHome(frame);
						frame.setContentPane(panel);
						frame.setBounds(100, 100, 525, 400);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
