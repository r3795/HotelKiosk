package hotel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Userorder {
	Scanner scan = new Scanner(System.in);
	String userId;
	User user;
	String TF;
	String itemName;
	String [] item = new String[10];
	int [] price = new int[10];
	String [] count = new String[10];
	String [] result = new String[10];
	String [] resultsum = new String[10];
	int i;
	int pricesum;
	public void readCh(String id)
	{
		userId = id;
		user = Admin.findUser(userId);
		while(true)
		{
			System.out.printf("��ǰ �̸��� �Է��ϼ���: ");
			itemName = scan.next();
			Item items = Admin.findItem(itemName);
			if(items == null)
			{
				System.out.printf("�ش� ��ǰ ���� ��Ȯ�� �Է����ּ���.\n");
				continue;
			}
			item[i] = items.itemCode;
			price[i] = items.price;
			System.out.printf("��ǰ ������ �Է��ϼ���: ");
			count[i] = scan.next();
			pricesum = pricesum + price[i] * Integer.parseInt(count[i]);
			i++;
			System.out.printf("�� �����Ͻðڽ��ϱ�?(��/�ƴϿ�): ");
			TF = scan.next();
			if(TF.equals("��"))
			{
				continue;
			}
			else
			{
				System.out.printf("�� �ݾ� : %d��\n", pricesum);
				System.out.printf("�����Ͻðڽ��ϱ�?(��/�ƴϿ�): ");
				TF = scan.next();
				if(TF.equals("��"))
				{
					if(user.mileage > 0)
					{
						System.out.printf("���ϸ����� ����Ͻðڽ��ϱ�? ���� ���ϸ���:%d (��/�ƴϿ�)\n", user.mileage);
						TF = scan.next();
						if(TF.equals("��"))
						{
							System.out.printf("���ϸ��� ���� �� �����Ǿ����ϴ�.\n");
							user.submileage(pricesum);
							break;
						}
						else
						{
							System.out.printf("���ϸ��� ��� ���� �����Ǿ����ϴ�.\n");
							break;
						}
					}
					else
					{
						System.out.printf("�����Ǿ����ϴ�.\n");
						break;
					}
				}
				pricesum = 0;
			}
		}
		int sum = 0;
		for (int j = 0; j < i; j++)
		{
			result[j] = item[j].concat(" "+count[j]);
		}
		if(i == 1)
			resultsum[0] = result[0];
		else if(i == 2)
			resultsum[0] = result[0].concat(" "+result[1]);
		else
		{
			resultsum[0] = result[0].concat(" "+result[1]);
			for (int j = 1; j < i - 1; j++)
			{
				resultsum[j] = resultsum[j-1].concat(" "+result[j+1]);
				sum = j;
			}
		}
		resultsum[sum+1] = userId.concat(" "+resultsum[sum]);
		resultsum[sum+2] = resultsum[sum+1].concat(" "+"0");
		String total = resultsum[sum+2];
		
		try {
		    	OutputStream output = new FileOutputStream("userorder.txt");
		    	byte[] by = total.getBytes();
		    	output.write(by);
		    	System.out.printf("�ֹ��Ǿ����ϴ�. \n");
		    	output.close();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}
}
