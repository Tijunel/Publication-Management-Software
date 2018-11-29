package back_end;

import java.io.Serializable;

public class Promotion implements Serializable
{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String documentName;
	@SuppressWarnings("unused")
	private String authorName;
	private Publication publication;
	
	public Promotion(String documentName, String authorName)
	{
		this.documentName = documentName;
		this.authorName = authorName;
	}
	
	public Promotion(Publication pub)
	{
		this.publication = pub;
		//this.publication.setIBSN(IBSN);
		//this.publication.setTitle(title);
		//this.publication.setAuthor(author);
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
}
