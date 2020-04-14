package teamassignment;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookManager extends DBManager{

	
	ArrayList<Book> book_list = new ArrayList<Book>();
	
	public BookManager() {
		connectDB();
		readDB();
	}

	@Override
	protected void readDB() {
		String sql = "select * from book";
		if(conn!=null)
		{
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					book_list.add(new Book(rs.getString(1), rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
				} 
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void showAll() {
		// TODO Auto-generated method stub
		for(int i=0;i<book_list.size();i++) {
			System.out.println(book_list.get(i));
		}
	}
	
	public Book getBook(String id) {
		for(int i=0;i<book_list.size();i++) {
			if(book_list.get(i).getId().equals(id)) {
				return book_list.get(i);
			}
		}
		return null;
	}




}
