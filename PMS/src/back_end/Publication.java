package back_end;

public class Publication //==Document
{
	private int IBSN;
	private String title;
	private String author;
	
	public Publication(int IBSN, String title, String author)
	{
		this.IBSN = IBSN;
		this.title = title;
		this.author = author;
	}
}
