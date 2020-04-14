package teamassignment;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BorrowManger extends DBManager{
	ArrayList<BorrowedBook> borrow_list = new ArrayList<BorrowedBook>();
	
	BookManager bm;
	UserManager um;
	String today;
	public BorrowManger(BookManager bm, UserManager um, String today) {
		super();
		this.bm = bm;
		this.um = um;
		this.today = today;
		connectDB();
		readDB();
	}
	

	@Override
	protected void readDB() {
		// TODO Auto-generated method stub
		String sql = "select * from borrowed_book";
		if(conn!=null)
		{
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					Book book = bm.getBook(rs.getString(1));
					User user = um.getUser(rs.getString(2));
					String borrow_day = rs.getString(3);
					borrow_list.add(new BorrowedBook(book, user, borrow_day));
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
		for(int i=0;i<borrow_list.size();i++) {
			System.out.println(borrow_list.get(i));
		}
	}
	public void showAll(User user)  {
		// TODO Auto-generated method stub
		for(int i=0;i<borrow_list.size();i++) {
			if(borrow_list.get(i).getUser().equals(user)) {
//				System.out.println("testestest");
//				System.out.println(borrow_list.get(i).getBorrowDay());
//				System.out.println(today);
				System.out.println(diffOfDate(borrow_list.get(i).getBorrowDay(), today));
				if(diffOfDate(borrow_list.get(i).getBorrowDay(), today)>7) {
					System.out.print(borrow_list.get(i));
					System.out.println(" 연체되었습니다!!!");
				}else
					System.out.println(borrow_list.get(i));
			}
		}
	}
	public void Borrow(User user, String book_id) {
		String sql = "insert into borrowed_book values (?,?,?)";

		if(conn!=null)
		{
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book_id);
				pstmt.setString(2, user.getId());
				pstmt.setString(3, today);
				pstmt.execute();
				System.out.println("삽입 성공");
				borrow_list.add(new BorrowedBook(bm.getBook(book_id), user, today));
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else
			System.out.println("삽입 실패");
	}
	public void Return(User user, String book_id) {
		
        String sql = "delete from borrowed_book where book_id = ? and user_id = ?";
               
		if(conn!=null)
		{
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book_id);
				pstmt.setString(2, user.getId());
				pstmt.execute();
				
				for(int i=0;i<borrow_list.size();i++) {
					if(borrow_list.get(i).getBook().getId().equals(book_id)&&borrow_list.get(i).getUser().equals(user)) {
						System.out.println("반납 성공");
						borrow_list.remove(i);
					}
				}
				
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else
			System.out.println("삭제 실패");

	}
	
	public static long diffOfDate(String begin, String end)
	  {
	    try{ // String Type을 Date Type으로 캐스팅하면서 생기는 예외로 인해 여기서 예외처리 해주지 않으면 컴파일러에서 에러가 발생해서 컴파일을 할 수 없다.
	        SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
	        // date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
	        Date FirstDate = format.parse(begin);
	        Date SecondDate = format.parse(end);
	        
	        // Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
	        // 연산결과 -950400000. long type 으로 return 된다.
	        long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	        
	        // Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다. 
	        // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
	        long calDateDays = calDate / ( 24*60*60*1000); 
	 
	        calDateDays = Math.abs(calDateDays);
	        System.out.println(calDateDays);
	        return calDateDays;
	        }
	        catch(ParseException e)
	        {
	            // 예외 처리
	        	System.out.println("오류");
	        }
		return 0;


	
	  }
	

}
