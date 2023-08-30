package hotel;

import java.util.Scanner;

import mgr.Manageable;

public class Reservation implements Manageable {
	String userId;
	String roomNumber;
	int month;
	int date;
	int period;
	int peoplenum;
	User user;
	Room room;
	int reserveback;
	int pricesum;
	int reservemile;
	@Override
	public void read(Scanner scan) {
		userId = scan.next();
		roomNumber = scan.next();
		month = scan.nextInt();
		date = scan.nextInt();
		period = scan.nextInt();
		peoplenum = scan.nextInt();
		user = Admin.findUser(userId);
		room = Admin.findRoom(roomNumber);
		user.addReservation(this);
		room.reservedate.adddate(month, date, period);
		mileageCheck();
	}
	void mileageCheck()
	{
		pricesum = Integer.parseInt(room.price) * period * peoplenum;
		reservemile = pricesum / 100;
		user.addreservemileage(this);
	}
	@Override
	public void print() {
		print(true);
	}
	void print(boolean bDetail) {
		System.out.printf("[%s님] [%s호] [%s월 %s일 %d박%d일 %d인 예약]", userId, roomNumber, month, date, period, period+1, peoplenum);
		if(reserveback > 0)
			System.out.printf(" <- 취소\n");
		else
			System.out.println();
	}
	@Override
	public boolean matches(String kwd) {
		if(userId.contentEquals(kwd))
			return true;
		if(roomNumber.contentEquals(kwd))
			return true;
		if((""+date).equals(kwd))
			return true;
		return false;
	}
	@Override
	public void modify(Scanner scan) {
		System.out.printf("[%s호] [예약 날짜 : %d월 %d일 %d박%d일] (1)예약취소 (2)뒤로가기", roomNumber, month, date, period, period+1);
		int num = scan.nextInt();
		switch(num) {
		case 1 : room.reservedate.deldate(month, date, period); reserveback = 1; user.stayPeriodReset(this); break;
		default : break;
		}
	}
}
