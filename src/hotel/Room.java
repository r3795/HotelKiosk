package hotel;

import java.util.Scanner;

import mgr.Manageable;

public class Room implements Manageable {
	String roomNumber;
	String roomSize;
	String roomItems;
	String price;
	Item item;
	int peoplenum;
	int Itemnum;
	int Itemcount;
	String [] roomItem = new String[10];
	ReserveDate reservedate = new ReserveDate();
	@Override
	public void read(Scanner scan) {
		roomNumber = scan.next();
		roomSize = scan.next();
		price = scan.next();
		peoplenum = scan.nextInt();
		Itemnum = scan.nextInt();
		while(Itemcount < Itemnum)
		{
			roomItems = scan.next();
			item = Admin.findItem(roomItems);
			item.CheckItem(this);
			Itemcount++;
		}
		reservedate.init();
	}
	@Override
	public void print() {
		print(true);
	}
	void print(boolean bDetail) {
		System.out.printf("[%s호] - [%s평형 - 1박 당 %s원] [최대 %d인] ", roomNumber, roomSize, price, peoplenum);
		if(Itemcount > 0)
			System.out.printf(" >> 객실 추가 제공 물품 : ");
		for(int k = 0; k < Itemcount; k++)
		{
			System.out.printf("[%s] ", roomItem[k]);
		}
		System.out.println();
		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j < 31; j++)
			{
				if(i==1||i==3||i==5||i==8||i==10)
					if(j==30)
						break;
				if(reservedate.check(i,j))
				{
					System.out.printf("[%d월 %d일] / ", i+1, j+1);
				}
			}
		}
		System.out.println();
	}
	@Override
	public boolean matches(String kwd) {
		if(kwd.contentEquals(roomNumber))
			return true;
		if(kwd.contentEquals(roomSize))
			return true;
		return false;
	}
	void CheckItems(String itemName)
	{
		roomItem[Itemcount] = itemName;
	}
	@Override
	public void modify(Scanner scan) {
		System.out.print("(1)객실크기수정 (2)객실수용인원수정 (3)객실가격수정");
		int num = scan.nextInt();
		switch(num) {
		case 1 : roomSize = scan.next(); break;
		case 2 : peoplenum = scan.nextInt(); break;
		case 3 : price = scan.next(); break;
		default : break;
		}
	}
}
