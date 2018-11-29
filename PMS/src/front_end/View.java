package front_end;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import users.User;

public abstract class View extends JFrame {
	private static final long serialVersionUID = 1L;
	protected ArrayList <JPanel> pages;
	protected Client client;
	protected User user;
}
