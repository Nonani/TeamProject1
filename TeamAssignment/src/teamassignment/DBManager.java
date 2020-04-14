package teamassignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBManager {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	protected void connectDB() {
		try { 
			Class.forName("org.mariadb.jdbc.Driver"); 
			conn = DriverManager.getConnection( "jdbc:mariadb://127.0.0.1:3306/mydb", "root", "1234"); 
			if( conn != null ) 
			{ 
				//System.out.println("DB 접속 성공 "); 
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
	protected abstract void readDB();
	public abstract void showAll();
	
	

}

