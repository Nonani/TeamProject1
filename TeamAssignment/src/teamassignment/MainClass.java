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
			System.out.println("�α��� : 1 / ȸ������ : 2");
			System.out.println("���Ḧ ���ҽ� 1�� 2 ���� �ƹ�Ű�� �Է��ϼ���.");
			switch (scan.nextLine().charAt(0)) {
			case '1': {
				System.out.println("( �α��� )");
				System.out.print("���̵� : ");
				String id = scan.nextLine();
				System.out.print("��й�ȣ : ");
				String pwd = scan.nextLine();
				if(id.equals("admin")&&pwd.equals("admin")) {
					AdminMode();
				}else {
					if(um.Login(id, pwd))
						UserMode();
					else
						System.out.println("���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
				break;
			}
			case '2':{
				System.out.println(" ( ȸ������ )");
				System.out.print("���̵� : ");
				String id = scan.nextLine();
				System.out.print("��й�ȣ : ");
				String pwd = scan.nextLine();
				System.out.print("�̸� : ");
				String name = scan.nextLine();
				System.out.print("��ȭ��ȣ : ");
				String phone = scan.nextLine();
				um.AddUser(new User(id, pwd, name, phone));
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
	public static void UserMode() {
		System.out.println("����� ���");
	}
}

