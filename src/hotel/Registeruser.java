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
		System.out.printf("아이디를 입력하세요: ");
		userId = scan.next();
		user = Admin.findUser(userId);
		while(user != null || userId.equals("admin"))
		{
			System.out.println("사용 불가한 아이디입니다. 다시 입력해주세요.");
			System.out.printf("아이디를 입력하세요: ");
			userId = scan.next();
			user = Admin.findUser(userId);
		}
		System.out.printf("비밀번호를 입력하세요: ");
		userPwd = scan.next();
		System.out.printf("성함을 입력하세요: ");
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
		    	System.out.printf("가입되었습니다. \n");
		    	output.close();
		    	
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}

}
