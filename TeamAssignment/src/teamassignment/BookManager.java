package teamassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lab.Word;



public class BookManager {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	ArrayList<Book> book_list = new ArrayList<Book>();
	
	public BookManager() {
		// TODO Auto-generated constructor stub
		connectDB();
		readDB();
	}
	private void connectDB() {
		try { 
			Class.forName("org.mariadb.jdbc.Driver"); 
			conn = DriverManager.getConnection( "jdbc:mariadb://127.0.0.1:3306/mydb", "root", "1234"); 
			if( conn != null ) 
			{ 
				System.out.println("DB 접속 성공 "); 
			} 
		} catch (ClassNotFoundException e) {
			System.out.println("[JDBC Connector Driver Error : " + e.getMessage() + "]");
			System.out.println("JDBC 드라이버 로드 실패 ");
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}
	}
	private void readDB() {
		String sql = "select * from book";
		if(conn!=null)
		{
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					book_list.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
				} 
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public void showAll() {
		for(int i=0;i<book_list.size();i++) {
			System.out.println(book_list.get(i));
		}
	}
}
