package hotel;

import java.util.Scanner;

public class Admin {
	void adminMenu(Scanner scan) {
		int num;
		while (true) {
			System.out.print("(1)물품출력 (2)사용자출력 (3)객실출력 (4)예약출력 (5)주문출력 (6)컴플레인출력 (7)후기출력 (8)수정 (기타) 종료 ");
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
			System.out.print("(1)물품수정 (2)사용자수정 (3)객실수정 (4)예약취소 (기타) 종료 ");
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
