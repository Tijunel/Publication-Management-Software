package back_end;

import java.io.Serializable;

public class Publication implements Serializable{ //==Document
	private static final long serialVersionUID = 1L;
	private int IBSN;
	private int id;
	private int copies;
	private String title;
	private String author;
	private String path;
	
	public Publication(int IBSN, String title, String author){
		this.setIBSN(IBSN);
		this.setTitle(title);
		this.setAuthor(author);
	}
	public Publication(int IBSN, String title, String author, int copies){
		this.setIBSN(IBSN);
		this.setTitle(title);
		this.setAuthor(author);
		this.setCopies(copies);
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

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}
}
