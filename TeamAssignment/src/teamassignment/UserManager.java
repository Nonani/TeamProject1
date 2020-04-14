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
				System.out.println("삽입 성공");
				user_list.add(user);
			}catch (SQLException e) {
				// TODO: handle exception
				System.out.println("중복된 아이디가 존재합니다.");
				//e.printStackTrace();
			}
		}
		else
			System.out.println("삽입 실패");
	}
	public boolean Login(String id, String pwd){	//로그인 확인 : 절차 아이디 비번이 존재하면 true
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
	public User getUser(String id) {
		for(int i=0;i<user_list.size();i++) {
			if(user_list.get(i).getId().equals(id)) {
				return user_list.get(i);
			}
		}
		return null;
	}
	public void UpdateUserInfo(String id, String changedpwd, String changedname, String changedphone) {
		User user = getUser(id);
		String sql = "update user set password = ?, name = ?, phone = ? where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);

			if(!changedpwd.equals("")) {
				user.setPassword(changedpwd);
				
			}
			if(!changedname.equals("")) {
				user.setPassword(changedname);
			}
			if(!changedphone.equals("")) {
				user.setPassword(changedphone);
			}
			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getId());
			pstmt.execute();
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
