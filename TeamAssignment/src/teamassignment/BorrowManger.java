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
					System.out.println(" ��ü�Ǿ����ϴ�!!!");
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
				System.out.println("���� ����");
				borrow_list.add(new BorrowedBook(bm.getBook(book_id), user, today));
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else
			System.out.println("���� ����");
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
						System.out.println("�ݳ� ����");
						borrow_list.remove(i);
					}
				}
				
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else
			System.out.println("���� ����");

	}
	
	public static long diffOfDate(String begin, String end)
	  {
	    try{ // String Type�� Date Type���� ĳ�����ϸ鼭 ����� ���ܷ� ���� ���⼭ ����ó�� ������ ������ �����Ϸ����� ������ �߻��ؼ� �������� �� �� ����.
	        SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
	        // date1, date2 �� ��¥�� parse()�� ���� Date������ ��ȯ.
	        Date FirstDate = format.parse(begin);
	        Date SecondDate = format.parse(end);
	        
	        // Date�� ��ȯ�� �� ��¥�� ����� �� �� ���ϰ����� long type ������ �ʱ�ȭ �ϰ� �ִ�.
	        // ������ -950400000. long type ���� return �ȴ�.
	        long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	        
	        // Date.getTime() �� �ش糯¥�� ��������1970�� 00:00:00 ���� �� �ʰ� �귶������ ��ȯ���ش�. 
	        // ���� 24*60*60*1000(�� �ð����� ���� ������) �� �����ָ� �ϼ��� ���´�.
	        long calDateDays = calDate / ( 24*60*60*1000); 
	 
	        calDateDays = Math.abs(calDateDays);
	        System.out.println(calDateDays);
	        return calDateDays;
	        }
	        catch(ParseException e)
	        {
	            // ���� ó��
	        	System.out.println("����");
	        }
		return 0;


	
	  }
	

}
