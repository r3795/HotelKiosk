package hotel;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Plusreserve {
	Scanner scan = new Scanner(System.in);
	String userId;
	String roomNumber;
	int month;
	int date;
	int period;
	int peoplenum;
	int stop;
	int datetmp;
	int dateend;
	int fullroom;
	int monthtmp;
	String TF;
	Room room;
	User user;
	
	public void readCh(String id)
	{
		userId = id;
		user = Admin.findUser(userId);
		while(stop == 0)
		{
			System.out.printf("���� ��ȣ�� �Է��ϼ���: ");
			roomNumber = scan.next();
			room = Admin.findRoom(roomNumber);
			while(room == null || !room.roomNumber.equals(roomNumber))
			{
				System.out.println("��� �Ұ��� �����Դϴ�. �ٽ� �Է����ּ���.");
				System.out.printf("���� ��ȣ�� �Է��ϼ���: ");
				roomNumber = scan.next();
				room = Admin.findRoom(roomNumber);
			}
			System.out.printf("���� ���� �Է��ϼ���: ");
			month = scan.nextInt();
			System.out.printf("���� ��¥�� �Է��ϼ���: ");
			date = scan.nextInt();
			System.out.printf("ü�� �Ⱓ�� �Է��ϼ���:(��) ");
			period =scan.nextInt();
			if(room != null) {
				int datetmp = date - 1;
				int monthtmp = month - 1;
				int dateend = date + period+1;
				int datesub = dateend - date;
				int num = 0;
				if(dateend > 31)
				{
					dateend -= 31;
				}
				else if(monthtmp==1||monthtmp==3||monthtmp==5||monthtmp==8||monthtmp==10)
				{
					if(dateend > 30)
					{
						dateend -= 30;
					}
				}
				while(num < datesub)
				{
					fullroom = 0;
					if(datetmp >= 31)
					{
						datetmp = 0;
						if(monthtmp == 11)
							monthtmp = 0;
						else
							monthtmp++;
					}
					else if(monthtmp==1||monthtmp==3||monthtmp==5||monthtmp==8||monthtmp==10)
					{
						if(datetmp >= 30)
						{
							datetmp = 0;
							monthtmp++;
						}
					}
					if(room.reservedate.check(monthtmp,datetmp))
					{
						System.out.printf("�ش� ������ ������ �Ұ��մϴ�. �̹� �����\n");
						fullroom = 1;
						break;
					}
					datetmp++;
					num++;
				}
				
				if(fullroom == 0)
				{
					System.out.printf("���� �ο��� �Է��ϼ���: ");
					peoplenum =scan.nextInt();
					if(peoplenum > 0 && peoplenum <= room.peoplenum)
					{
						int money = Integer.parseInt(room.price) * period * peoplenum;
						System.out.printf("�����Ͻðڽ��ϱ�? %d�� * %d(��) * %d(��) =  %d(��) (��/�ƴϿ�)\n",peoplenum, period, Integer.parseInt(room.price), money);
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
									user.submileage(money);
								}
								else
									System.out.printf("���ϸ��� ��� ���� �����Ǿ����ϴ�.\n");
							}
							else
								System.out.printf("�����Ǿ����ϴ�.\n");
						}
						else
						{
							System.out.printf("�ʱ�ȭ������ ���ư��ϴ�.\n");
							fullroom = 1;
						}
					}
					else
					{
						System.out.printf("���� �ο��� �´� ������ �ƴմϴ�.\n");
						System.out.printf("�ʱ�ȭ������ ���ư��ϴ�.\n");
						fullroom = 1;
					}
				}
				
				if(fullroom == 1)
					continue;
			}
			
			stop = 1;
		}
		String result = userId.concat(" "+roomNumber);
		String result1 = result.concat(" "+month);
		String result2 = result1.concat(" "+date);
		String result3 = result2.concat(" "+period);
		String result4 = result3.concat(" "+peoplenum);
		try {
		    	OutputStream output = new FileOutputStream("plusreserve.txt");
		    	byte[] by = result4.getBytes();
		    	output.write(by);
		    	System.out.printf("����Ǿ����ϴ�. \n");
		    	output.close();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}
}
