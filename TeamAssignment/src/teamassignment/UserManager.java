package teamassignment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager extends DBManager {

	ArrayList<User> user_list = new ArrayList<User>();
	
	public UserManager() {
		connectDB();
		readDB();
	}
	@Override
	protected void readDB() {
		String sql = "select * from user";
		if(conn!=null)
		{
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					user_list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
				} 
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	@Override
	public void showAll() {
		for(int i=0;i<user_list.size();i++) {
			System.out.println(user_list.get(i));
		}
	}
	public void AddUser(User user) {
		String sql = "insert into user values (?,?,?,?)";

		if(conn!=null)
		{
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getPhone());
				pstmt.execute();
				System.out.println("���� ����");
			}catch (SQLException e) {
				// TODO: handle exception
				System.out.println("�ߺ��� ���̵� �����մϴ�.");
				//e.printStackTrace();
			}
		}
		else
			System.out.println("���� ����");
	}
	public boolean Login(String id, String pwd){
		String sql = "select * from user where id = ? and password = ?";
		PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();
 
            if (rs.next()) {
            	return true;
            }
            else return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
	}
}
