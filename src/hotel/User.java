package hotel;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
		String userId;
		String userPwd;
		String userName;
		int stayPeriod;
		int mileage;
		Reservation reserve;
		ArrayList<Reservation> myReserveList = new ArrayList<>();
		ArrayList<Order> myOrderList = new ArrayList<>();
		@Override
		public void read(Scanner scan) {
			userId = scan.next();
			userPwd = scan.next();
			userName = scan.next();
			stayPeriod = scan.nextInt();
			mileage = scan.nextInt();
		}
		@Override
		public void print() {
			System.out.printf("[���̵� : %s] [���� : %s] [���ϸ��� : %s��] [�� �̿����ֽ� �Ⱓ : %d��]\n",
					userId, userName, mileage, stayPeriod);
			for (Reservation rs: myReserveList)
				rs.print(false);
			for (Order od: myOrderList)
				od.print(false);
		}
		void addReservation(Reservation reserve) {
			stayPeriod += reserve.period;
			myReserveList.add(reserve);
		}
		void stayPeriodReset(Reservation reserve) {
			stayPeriod -= reserve.period;
			myReserveList.remove(reserve);
		}
		@Override
		public boolean matches(String kwd) {
			if (userId.contentEquals(kwd))
				return true;
			if (userName.contentEquals(kwd))
				return true;
			for (Reservation rs: myReserveList)
				if (rs.matches(kwd))
					return true;
			return false;
		}
		@Override
		public void modify(Scanner scan) {
			System.out.print("(1)����ھ��̵���� (2)����ں�й�ȣ���� (3)������̸����� (4)��ü���Ⱓ���� (5)���ϸ������� ");
			int num = scan.nextInt();
			switch(num) {
			case 1 : reserve = Admin.findReserve(userId); userId = scan.next(); reserve.userId = userId; break;
			case 2 : userPwd = scan.next(); break;
			case 3 : userName = scan.next(); break;
			case 4 : stayPeriod = scan.nextInt(); break;
			case 5 : mileage = scan.nextInt(); break;
			default : break;
			}
			
		}
		void addOrder(Order order) {
			myOrderList.add(order);	
		}
		public void addreservemileage(Reservation reserve) {
			mileage += reserve.reservemile;
		}
		public void addordermileage(Order order) {
			mileage += (order.total / 100);
		}
		public void submileage(int sum) {
			mileage -= sum;
			if(mileage < 0)
				mileage = 0;
		}
}
