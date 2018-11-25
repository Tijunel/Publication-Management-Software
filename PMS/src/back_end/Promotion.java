package back_end;

public class Promotion 
{
	private String documentName;
	private String authorName;
	private Publication publication;
	
	public Promotion(String documentName, String authorName)
	{
		this.documentName = documentName;
		this.authorName = authorName;
	}
	
	public Promotion(int id, int IBSN, String title, String author, int copies)
	{
		this.publication.setId(id);
		this.publication.setIBSN(IBSN);
		this.publication.setTitle(title);
		this.publication.setAuthor(author);
		this.publication.setCopies(copies);
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
}
