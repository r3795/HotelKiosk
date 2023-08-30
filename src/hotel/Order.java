package hotel;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class Order implements Manageable {
	String userId;
	int total;
	User user;
	ArrayList<Item> orderedItemList = new ArrayList<>();
	ArrayList<Integer> orderedItemCount = new ArrayList<>();
	@Override
	public void read(Scanner scan) {
		userId = scan.next();
		user = Admin.findUser(userId);
		String itemId = scan.next();
		Item item = null;
		while (!itemId.contentEquals("0")) {
			item = Admin.findItem(itemId);
			if (item == null) {
				System.out.printf("ItemId Error: %s", itemId);
				continue;
			}
			orderedItemList.add(item);
			orderedItemCount.add(scan.nextInt());
			itemId = scan.next();
		}
		calcTotal();
		user.addordermileage(this);
		user.addOrder(this);
		
	}
	void calcTotal() {
		for (int i = 0; i < orderedItemList.size(); i++) {
			total += orderedItemList.get(i).getSubtotal(orderedItemCount.get(i));
		}
	}
	public void print() {
		print(true);
	}
	void print(boolean bDetail) {
		System.out.printf("[%s님] ", userId);
		System.out.printf(" - 주문금액:%5d원 \n", total);
		if (!bDetail)
			return;
		for (int i = 0; i < orderedItemList.size(); i++) {
			System.out.printf("\t[%d개]", orderedItemCount.get(i));
			orderedItemList.get(i).print();
		}
	}
	@Override
	public boolean matches(String kwd) {
		if (userId.equals(kwd))
			return true;
		for (Item item: orderedItemList) 
			if (item.matches(kwd))
				return true;
		return false;
	}

	@Override
	public void modify(Scanner scan) {
		// TODO Auto-generated method stub
		
	}

}
