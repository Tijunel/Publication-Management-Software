package data;

public class Subscription 
{
	private boolean valid;
	
	public Subscription(boolean valid)
	{
		this.setValid(valid);
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
