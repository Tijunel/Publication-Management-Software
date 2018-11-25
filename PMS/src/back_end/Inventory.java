package back_end;

import java.util.ArrayList;

public class Inventory 
{
	private ArrayList<Publication> publications;
	
	public Inventory(ArrayList<Publication> publications)
	{
		this.setPublications(publications);
	}

	public ArrayList<Publication> getPublications() {
		return publications;
	}

	public void setPublications(ArrayList<Publication> publications) {
		this.publications = publications;
	}
}
