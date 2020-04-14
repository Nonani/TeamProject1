package teamassignment;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.print("������ ��¥�� �Է��ϼ��� ex)20200414 : ");
			String today = scan.nextLine();
			BookManager bookm = new BookManager();
//			bookm.showAll();
			UserManager userm = new UserManager();
			BorrowManger borrowm = new BorrowManger(bookm, userm, today);
//			borrowm.showAll();
//			userm.showAll();
			
			System.out.println("�α��� : 1 / ȸ������ : 2");
			System.out.println("���Ḧ ���ҽ� 1�� 2 ���� �ƹ�Ű�� �Է��ϼ���.");
			switch (scan.nextLine().charAt(0)) {
			case '1': {
				System.out.println("( �α��� )");
				System.out.print("�й� : ");
				String id = scan.nextLine();
				System.out.print("��й�ȣ : ");
				String pwd = scan.nextLine();
				if(id.equals("admin")&&pwd.equals("admin")) {
					AdminMode();
				}else {
					if(userm.Login(id, pwd)) {
						System.out.println("����� ���");
						User user = userm.getUser(id);
						if(user == null)
						{
							System.out.println("���� ������ ã�� �� �����ϴ�.");
							break;
						}
						System.out.println("1 : �������� / 2 : ���� / 3 : �ݳ� / 4 : ��������");
						System.out.print("input : ");
						String input = scan.nextLine();
						switch(input.charAt(0)) {
						case '1':
							System.out.println(user);
							break;
						case '2':
						{
							bookm.showAll();
							System.out.print("������ å�� id�� �Է��ϼ��� : ");
							String book_id = scan.nextLine();
							borrowm.Borrow(user, book_id);
							
						}
							break;
						case '3':
							borrowm.showAll(user);
							System.out.print("�ݳ��� å�� id�� �Է��ϼ��� : ");
							String book_id = scan.nextLine();
							borrowm.Return(user, book_id);
							break;
						case '4':
							borrowm.showAll(user);
							break;
						default:
								
							
						}
					}else
						System.out.println("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
				break;
			}
			case '2':{
				System.out.println(" ( ȸ������ )");
				System.out.print("�й� : ");
				String id = scan.nextLine();
				System.out.print("��й�ȣ : ");
				String pwd = scan.nextLine();
				System.out.print("�̸� : ");
				String name = scan.nextLine();
				System.out.print("��ȭ��ȣ : ");
				String phone = scan.nextLine();
				userm.AddUser(new User(id, pwd, name, phone));
			}
				
				
				break;
			default:
				System.out.println("���α׷� ����");
				
				return;
			}
		}
		
		
	}	
	public static void AdminMode() {
		System.out.println("������ ���");
		
	}
}

