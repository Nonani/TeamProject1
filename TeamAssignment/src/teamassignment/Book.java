package teamassignment;

public class Book {
	private String id;
	private String symbol;
	private String title;
	private String author;
	private String published;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	
	public Book(String id, String symbol, String title, String author, String published) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.title = title;
		this.author = author;
		this.published = published;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", symbol=" + symbol + ", title=" + title + ", author=" + author + ", published="
				+ published + "]";
	}
	
	

	
	
	
}
