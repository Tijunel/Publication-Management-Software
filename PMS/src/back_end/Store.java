package back_end;

import java.util.ArrayList;

public class Store 
{
	private Inventory inventory;
	private ArrayList<Promotion> promotions;
	
	public Store(Inventory inventory, ArrayList<Promotion> promotions)
	{
		this.inventory = inventory;
		this.promotions = promotions;
	}
	
	public Publication search(String key)
	{
		return new Publication(1, "", "");
	}
}
