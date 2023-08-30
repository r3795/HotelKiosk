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
			System.out.printf("객실 번호를 입력하세요: ");
			roomNumber = scan.next();
			room = Admin.findRoom(roomNumber);
			while(room == null || !room.roomNumber.equals(roomNumber))
			{
				System.out.println("사용 불가한 객실입니다. 다시 입력해주세요.");
				System.out.printf("객실 번호를 입력하세요: ");
				roomNumber = scan.next();
				room = Admin.findRoom(roomNumber);
			}
			System.out.printf("예약 월을 입력하세요: ");
			month = scan.nextInt();
			System.out.printf("예약 날짜를 입력하세요: ");
			date = scan.nextInt();
			System.out.printf("체류 기간을 입력하세요:(박) ");
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
						System.out.printf("해당 객실은 예약이 불가합니다. 이미 예약됨\n");
						fullroom = 1;
						break;
					}
					datetmp++;
					num++;
				}
				
				if(fullroom == 0)
				{
					System.out.printf("투숙 인원을 입력하세요: ");
					peoplenum =scan.nextInt();
					if(peoplenum > 0 && peoplenum <= room.peoplenum)
					{
						int money = Integer.parseInt(room.price) * period * peoplenum;
						System.out.printf("결제하시겠습니까? %d인 * %d(박) * %d(원) =  %d(원) (네/아니오)\n",peoplenum, period, Integer.parseInt(room.price), money);
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
									user.submileage(money);
								}
								else
									System.out.printf("마일리지 사용 없이 결제되었습니다.\n");
							}
							else
								System.out.printf("결제되었습니다.\n");
						}
						else
						{
							System.out.printf("초기화면으로 돌아갑니다.\n");
							fullroom = 1;
						}
					}
					else
					{
						System.out.printf("투숙 인원에 맞는 객실이 아닙니다.\n");
						System.out.printf("초기화면으로 돌아갑니다.\n");
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
		    	System.out.printf("예약되었습니다. \n");
		    	output.close();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}
}
