package back_end;

public class Publication //==Document
{
	private int IBSN;
	private int id;
	private String title;
	private String author;
	private String path;
	
	public Publication(int IBSN, String title, String author)
	{
		this.setIBSN(IBSN);
		this.setTitle(title);
		this.setAuthor(author);
	}
	
	public Publication(int id, int IBSN, String title, String author)
	{
		this.setId(id);
		this.setIBSN(IBSN);
		this.setTitle(title);
		this.setAuthor(author);
	}

	public int getIBSN() {
		return IBSN;
	}

	public void setIBSN(int iBSN) {
		IBSN = iBSN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
