package teamassignment;

public class BorrowedBook {
	private Book book;
	private User user;
	private String BorrowDay;
	
	
	public BorrowedBook(Book book, User user, String borrowDay) {
		super();
		this.book = book;
		this.user = user;
		BorrowDay = borrowDay;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getBorrowDay() {
		return BorrowDay;
	}


	public void setBorrowDay(String borrowDay) {
		BorrowDay = borrowDay;
	}


	@Override
	public String toString() {
		return "BorrowedBook [book=" + book + ", user=" + user + ", BorrowDay=" + BorrowDay + "]";
	}


	
	
	
	
}
