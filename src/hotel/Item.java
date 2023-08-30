package hotel;

import java.util.Scanner;

import mgr.Manageable;

public class Item implements Manageable {
	String itemCode;
	String itemName;
	int price;
	@Override
	public void read(Scanner scan) {
		itemCode = scan.next();
		itemName = scan.next();
		price = scan.nextInt();
	}
	@Override
	public void print() {
		System.out.printf("[%s]", itemName);
		System.out.printf("[%5d원]\n", price);
	}
	@Override
	public boolean matches(String kwd) {
		if (itemName.equals(kwd))
		    return true;
		if (itemCode.equals(kwd))
		    return true;
		return false;
    }
	boolean matches(String[] kwdArr) {
		for (String kwd: kwdArr) {
			if (!matches(kwd))
				return false;
		}
		return true;
	}
	void CheckItem(Room room)
	{
		room.CheckItems(itemName);
	}
	int getSubtotal(int count) {
		return price * count;
	}
	@Override
	public void modify(Scanner scan) {
		System.out.print("(1)물품코드수정 (2)물품이름수정 (3)물품가격수정");
		int num = scan.nextInt();
		switch(num) {
		case 1 : itemCode = scan.next(); break;
		case 2 : itemName = scan.next(); break;
		case 3 : price = scan.nextInt(); break;
		default : break;
		}
	}
}
