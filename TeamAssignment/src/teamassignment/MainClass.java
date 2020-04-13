package teamassignment;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true) {
			BookManager bm = new BookManager();
//			m.showAll();
			UserManager um = new UserManager();
//			m.showAll();
			Scanner scan = new Scanner(System.in);
			System.out.println("로그인 : 1 / 회원가입 : 2");
			System.out.println("종료를 위할시 1과 2 외의 아무키나 입력하세요.");
			switch (scan.nextLine().charAt(0)) {
			case '1': {
				System.out.println("( 로그인 )");
				System.out.print("아이디 : ");
				String id = scan.nextLine();
				System.out.print("비밀번호 : ");
				String pwd = scan.nextLine();
				if(id.equals("admin")&&pwd.equals("admin")) {
					AdminMode();
				}else {
					if(um.Login(id, pwd))
						UserMode();
					else
						System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				}
				break;
			}
			case '2':{
				System.out.println(" ( 회원가입 )");
				System.out.print("아이디 : ");
				String id = scan.nextLine();
				System.out.print("비밀번호 : ");
				String pwd = scan.nextLine();
				System.out.print("이름 : ");
				String name = scan.nextLine();
				System.out.print("전화번호 : ");
				String phone = scan.nextLine();
				um.AddUser(new User(id, pwd, name, phone));
			}
				
				
				break;
			default:
				System.out.println("프로그램 종료");
				
				return;
			}
		}
		
		
	}	
	public static void AdminMode() {
		System.out.println("관리자 모드");
		
	}
	public static void UserMode() {
		System.out.println("사용자 모드");
	}
}

