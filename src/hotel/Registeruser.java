package hotel;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Registeruser {
	Scanner scan = new Scanner(System.in);
	String userId;
	String userPwd;
	String userName;
	int stayPeriod;
	int mileage;
	User user = null;
	
	public void readCh()
	{
		System.out.printf("���̵� �Է��ϼ���: ");
		userId = scan.next();
		user = Admin.findUser(userId);
		while(user != null || userId.equals("admin"))
		{
			System.out.println("��� �Ұ��� ���̵��Դϴ�. �ٽ� �Է����ּ���.");
			System.out.printf("���̵� �Է��ϼ���: ");
			userId = scan.next();
			user = Admin.findUser(userId);
		}
		System.out.printf("��й�ȣ�� �Է��ϼ���: ");
		userPwd = scan.next();
		System.out.printf("������ �Է��ϼ���: ");
		userName = scan.next();
		stayPeriod = 0;
		mileage = 0;
		String result = userId.concat(" "+userPwd+" ");
		String result1 = result.concat(userName+" ");
		String result2 = result1.concat(stayPeriod+" ");
		String result3 = result2.concat(mileage+"");
		try {
		    	OutputStream output = new FileOutputStream("registeruser.txt");
		    	byte[] by = result3.getBytes();
		    	output.write(by);
		    	System.out.printf("���ԵǾ����ϴ�. \n");
		    	output.close();
		    	
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}

}
