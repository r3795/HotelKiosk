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
			System.out.printf("물품 이름을 입력하세요: ");
			itemName = scan.next();
			Item items = Admin.findItem(itemName);
			if(items == null)
			{
				System.out.printf("해당 물품 명을 정확히 입력해주세요.\n");
				continue;
			}
			item[i] = items.itemCode;
			price[i] = items.price;
			System.out.printf("물품 개수를 입력하세요: ");
			count[i] = scan.next();
			pricesum = pricesum + price[i] * Integer.parseInt(count[i]);
			i++;
			System.out.printf("더 구매하시겠습니까?(네/아니오): ");
			TF = scan.next();
			if(TF.equals("네"))
			{
				continue;
			}
			else
			{
				System.out.printf("총 금액 : %d원\n", pricesum);
				System.out.printf("결제하시겠습니까?(네/아니오): ");
				TF = scan.next();
				if(TF.equals("네"))
				{
					if(user.mileage > 0)
					{
						System.out.printf("마일리지를 사용하시겠습니까? 현재 마일리지:%d (네/아니오)\n", user.mileage);
						TF = scan.next();
						if(TF.equals("네"))
						{
							System.out.printf("마일리지 차감 후 결제되었습니다.\n");
							user.submileage(pricesum);
							break;
						}
						else
						{
							System.out.printf("마일리지 사용 없이 결제되었습니다.\n");
							break;
						}
					}
					else
					{
						System.out.printf("결제되었습니다.\n");
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
		    	System.out.printf("주문되었습니다. \n");
		    	output.close();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}
}
