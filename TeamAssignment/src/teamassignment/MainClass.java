package teamassignment;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.print("오늘의 날짜를 입력하세요 ex)20200414 : ");
			String today = scan.nextLine();
			BookManager bookm = new BookManager();
//			bookm.showAll();
			UserManager userm = new UserManager();
			BorrowManger borrowm = new BorrowManger(bookm, userm, today);
//			borrowm.showAll();
//			userm.showAll();
			
			System.out.println("로그인 : 1 / 회원가입 : 2");
			System.out.println("종료를 위할시 1과 2 외의 아무키나 입력하세요.");
			switch (scan.nextLine().charAt(0)) {
			case '1': {
				System.out.println("( 로그인 )");
				System.out.print("학번 : ");
				String id = scan.nextLine();
				System.out.print("비밀번호 : ");
				String pwd = scan.nextLine();
				if(id.equals("admin")&&pwd.equals("admin")) {
					AdminMode();
				}else {
					if(userm.Login(id, pwd)) {
						System.out.println("사용자 모드");
						User user = userm.getUser(id);
						if(user == null)
						{
							System.out.println("유저 정보를 찾을 수 없습니다.");
							break;
						}
						System.out.println("1 : 개인정보 / 2 : 대출 / 3 : 반납 / 4 : 대출정보");
						System.out.print("input : ");
						String input = scan.nextLine();
						switch(input.charAt(0)) {
						case '1':
							System.out.println(user);
							break;
						case '2':
						{
							bookm.showAll();
							System.out.print("대출할 책의 id를 입력하세요 : ");
							String book_id = scan.nextLine();
							borrowm.Borrow(user, book_id);
							
						}
							break;
						case '3':
							borrowm.showAll(user);
							System.out.print("반납할 책의 id를 입력하세요 : ");
							String book_id = scan.nextLine();
							borrowm.Return(user, book_id);
							break;
						case '4':
							borrowm.showAll(user);
							break;
						default:
								
							
						}
					}else
						System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				}
				break;
			}
			case '2':{
				System.out.println(" ( 회원가입 )");
				System.out.print("학번 : ");
				String id = scan.nextLine();
				System.out.print("비밀번호 : ");
				String pwd = scan.nextLine();
				System.out.print("이름 : ");
				String name = scan.nextLine();
				System.out.print("전화번호 : ");
				String phone = scan.nextLine();
				userm.AddUser(new User(id, pwd, name, phone));
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
}

