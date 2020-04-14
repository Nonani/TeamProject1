package teamassignment;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		System.out.print("오늘의 날짜를 입력하세요 ex)20200414 : ");
		String today = scan.nextLine();
		while(true) {
			
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
				if(id.equals("admin")&&pwd.equals("admin")) {	//관리자 모드
					System.out.println("관리자 모드 입니다.");
					System.out.println("1 : 유저 정보 / 2 : 책 정보 / 3 : 대출 중인 책 / 4 : 연체 중인 책");
					System.out.print("input : ");
					String input = scan.nextLine();
					switch(input.charAt(0)) {
					case '1':
						userm.showAll();												
						break;
					case '2':
						bookm.showAll();
						break;
					case '3':
						borrowm.showAll();
						break;
					case '4':
						//아직 구현 안함
						break;
					default:
						System.out.println("잘못된 입력형식입니다!!");
						
					}
					
				}else {											//유저 모드
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
						{
							System.out.println(user);
							System.out.print("개인 정보를 수정하시겠습니까 ? (y/n): ");
							String str = scan.nextLine();
							if(str.equals("y")||str.equals("Y")) {
								System.out.println("변경을 원치 않을 경우 엔터를 눌러주세요!!");
								
								System.out.print("비밀번호 : ");
								String changedpwd = scan.nextLine();
								System.out.println(changedpwd);
								System.out.print("이름 : ");
								String changedname = scan.nextLine();
								System.out.print("전화번호 : ");
								String changedphone = scan.nextLine();
								userm.UpdateUserInfo(id, changedpwd, changedname, changedphone);
							}

						}														
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
							System.out.println("잘못된 입력형식입니다!!");
							
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

