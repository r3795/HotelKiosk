package hotel;

import java.util.Scanner;

public class Admin {
	void adminMenu(Scanner scan) {
		int num;
		while (true) {
			System.out.print("(1)��ǰ��� (2)�������� (3)������� (4)������� (5)�ֹ���� (6)���÷������ (7)�ı���� (8)���� (��Ÿ) ���� ");
			num = scan.nextInt();
			if (num < 1 || num > 8) break;
			switch(num) {
			case 1: Kiosk.itemMgr.printAll(); break;
			case 2: Kiosk.userMgr.printAll(); break;
			case 3: Kiosk.roomMgr.printAll(); break;
			case 4: Kiosk.reserveMgr.printAll(); break;
			case 5: Kiosk.orderMgr.printAll(); break;
			case 6: Kiosk.complaintMgr.printAll(); break;
			case 7: Kiosk.reviewMgr.printAll(); break;
			case 8: modifyMenu(scan); break;
			default: break;
			}
		}
	}
	void modifyMenu(Scanner scan) {
		int num;
		while (true) {
			System.out.print("(1)��ǰ���� (2)����ڼ��� (3)���Ǽ��� (4)������� (��Ÿ) ���� ");
			num = scan.nextInt();
			if (num < 1 || num > 4) break;
			switch(num) {
			case 1: Kiosk.itemMgr.modify(scan); break;
			case 2: Kiosk.userMgr.modify(scan); break;
			case 3: Kiosk.roomMgr.modify(scan); break;
			case 4: Kiosk.reserveMgr.modify(scan); break;
			default: break;
			}
		}
	}
	static Item findItem(String kwd) {
		return (Item)Kiosk.itemMgr.find(kwd);
	}
	static User findUser(String kwd) {
		return (User)Kiosk.userMgr.find(kwd);
	}
	static Room findRoom(String kwd) {
		return (Room)Kiosk.roomMgr.find(kwd);
	}
	static Order findOrder(String kwd) {
		return (Order)Kiosk.orderMgr.find(kwd);
	}
	static Reservation findReserve(String kwd) {
		return (Reservation)Kiosk.reserveMgr.find(kwd);
	}
}
