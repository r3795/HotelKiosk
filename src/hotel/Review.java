package hotel;

import java.util.Scanner;

import mgr.Manageable;

public class Review implements Manageable {
	String userId;
	String roomNumber;
	String data;
	String date;
	@Override
	public void read(Scanner scan) {
		userId = scan.next();
		roomNumber = scan.next();
		data = scan.next();
		date = scan.nextLine();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("%s��\n�̿� ���� : %s\n�ı� : %s\n���� : %s\n", userId, roomNumber, data, date);
	}

	@Override
	public boolean matches(String kwd) {
		if(data.contains(kwd))
			return true;
		return false;
	}

	@Override
	public void modify(Scanner scan) {
		System.out.print("(1)�ı���� ");
		int num = scan.nextInt();
		switch(num) {
		case 1 : data = scan.next(); break;
		default : break;
		}
	}
}
