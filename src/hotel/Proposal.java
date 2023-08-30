package hotel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Proposal {
	Scanner scan = new Scanner(System.in);
	String userId;
	String roomNumber;
	String data;
	Date date = new Date();
	String s;
	Reservation reserve;
	
	public Boolean readCh(String id)
	{
		userId = id;
		reserve = Admin.findReserve(userId);
		if(reserve == null)
		{
			System.out.printf("�̿����ֽ� ������ �����ϴ�. ���� �� �ۼ� ��Ź�帳�ϴ�.\n");
			return false;
		}
		else
		{
			roomNumber = reserve.roomNumber;
			System.out.printf("���ǻ����� �Է��ϼ���: ");
			data = scan.next();
			SimpleDateFormat formatType = new SimpleDateFormat("MM-dd a HH:mm:ss");
			s = formatType.format(date);
			String result = userId.concat(" "+roomNumber);
			String result1 = result.concat(" "+data);
			String result2 = result1.concat(" "+s);
			try {
				OutputStream output = new FileOutputStream("proposal.txt");
				byte[] by = result2.getBytes();
				output.write(by);
				System.out.printf("���ǻ����� ����Ǿ����ϴ�. \n");
				output.close();
				}
			catch (Exception e) {
				e.getStackTrace();
				}
			return true;
		}
	}
}
