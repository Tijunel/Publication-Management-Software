package model;

import control.Control;

public abstract class Model 
{
	private Control control;
	
	public Model(Control control)
	{
		this.control = control;
	}
}
