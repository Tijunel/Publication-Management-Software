package front_end;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import users.User;

public abstract class View extends JFrame 
{
	protected ArrayList <JPanel> pages;
	protected Client client;
	protected User user;
}
