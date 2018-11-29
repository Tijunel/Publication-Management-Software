package control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.Model;

public abstract class Control extends Thread{
	protected Model model;
	protected ObjectInputStream in;
	protected ObjectOutputStream out;
	
	public Control(ObjectInputStream in, ObjectOutputStream out){
		this.in = in;
		this.out = out;
	}
}
