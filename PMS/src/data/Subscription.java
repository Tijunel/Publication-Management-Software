package data;

import java.io.Serializable;

public class Subscription implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean valid;
	
	public Subscription(boolean valid){
		this.setValid(valid);
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
