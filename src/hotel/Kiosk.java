package hotel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import mgr.Factory;
import mgr.Manager;

public class Kiosk {
	private static Kiosk kiosk = null;
	private Kiosk() {}
	public static Kiosk getInstance() {
		if (kiosk == null)
			kiosk = new Kiosk();
		return kiosk;
	}
	Scanner scan = new Scanner(System.in);
	static Manager<Item> itemMgr = new Manager<>();
	static Manager<User> userMgr = new Manager<>();
	static Manager<Room> roomMgr = new Manager<>();
	static Manager<Order> orderMgr = new Manager<>();
	static Manager<Reservation> reserveMgr = new Manager<>();
	static Manager<Complaint> complaintMgr = new Manager<>();
	static Manager<Review> reviewMgr = new Manager<>();
	
	Admin admin = new Admin();
	String id;
	String pwd;
	User user = null;
	Reservation reserve;
	int pass;
	public void run() {
		itemMgr.readAll("items.txt", new Factory<Item>() {
			public Item create() {
				return new Item();
			}
		});
		userMgr.readAll("user.txt", new Factory<User>() {
			public User create() {
				return new User();
			}
		});
		roomMgr.readAll("room.txt", new Factory<Room>() {
			public Room create() {
				return new Room();
			}
		});
		orderMgr.readAll("order.txt", new Factory<Order>() {
			public Order create() {
				return new Order();
			}
		});
		reserveMgr.readAll("reservation.txt", new Factory<Reservation>() {
			public Reservation create() {
				return new Reservation();
			}
		});
		complaintMgr.readAll("complaint.txt", new Factory<Complaint>() {
			public Complaint create() {
				return new Complaint();
			}
		});
		reviewMgr.readAll("review.txt", new Factory<Review>() {
			public Review create() {
				return new Review();
			}
		});
		menu();
	}
	void menu() {
		int num;
		while (true) {
			pass = 0;
			System.out.print("(1)사용자로그인 (2)회원가입 (3)관리자로그인 (4)오시는 길 ");
			num = scan.nextInt();
			if (num < 1 || num > 4) break;
			switch(num) {
			case 1: userMenu(); break;
			case 2: Registeruser res = new Registeruser(); res.readCh();
			userMgr.readAll("registeruser.txt", new Factory<User>() {
				public User create() {
					return new User();
				}
			}); break;
			case 3: adminLogin(); break;
			case 4: directions();
			default: break;
			}
		}
	}
	private void directions() {
		try {
		File file = new File("directions.txt");
		
		FileReader file_reader = new FileReader(file);
        int cur = 0;
        while((cur = file_reader.read()) != -1){
           System.out.print((char)cur);
        }
        file_reader.close();
		}
        catch (FileNotFoundException e) {
        	e.getStackTrace();
        	}
        catch(IOException e){
           e.getStackTrace();
           }
		System.out.println();
		}
	void userMenu() {
		userLogin();
		int num;
		while (true) {
			System.out.print("(1)물품주문 (2)예약확인 (3)예약 (4)컴플레인 (5)후기 (기타)종료 ");
			num = scan.nextInt();
			if (num < 1 || num > 5) break;
			switch(num) {
			case 1: itemMgr.printAll(); Userorder uo = new Userorder(); uo.readCh(id);
			orderMgr.readAll("userorder.txt", new Factory<Order>() {
				public Order create() {
					return new Order();
					}
				});break;
			case 2: reserveMgr.findprint(id); break;
			case 3: roomMgr.printAll(); Plusreserve pr = new Plusreserve(); pr.readCh(id);
			reserveMgr.readAll("plusreserve.txt", new Factory<Reservation>() {
				public Reservation create() {
					return new Reservation();
					}
				}); break;
			case 4: Proposal pp = new Proposal(); if(pp.readCh(id)) {
			complaintMgr.readAll("proposal.txt", new Factory<Complaint>() {
				public Complaint create() {
					return new Complaint();
					}
				});} break;
			case 5: Comment cm = new Comment(); if(cm.readCh(id)) {
			reviewMgr.readAll("comment.txt", new Factory<Review>() {
				public Review create() {
					return new Review();
					}
				});} break;
			default: break;
			}
		}
	}
	
	void userLogin() {
		while(pass != 1)
		{
			System.out.printf("아이디를 입력하세요: ");
			id = scan.next();
			System.out.printf("비밀번호를 입력하세요: ");
			pwd = scan.next();
			user = userMgr.find(id);
			if(user == null || !user.userId.equals(id))
			{
				System.out.printf("아이디가 틀렸습니다.\n");
				continue;
			}
			if(!user.userPwd.equals(pwd))
			{
				System.out.printf("비밀번호가 틀렸습니다.\n");
				continue;
			}
			System.out.printf("%s님 환영합니다.\n", user.userName);
			pass = 1;
		}
	}
	void adminLogin() {
		while(pass != 1)
		{
			System.out.printf("아이디를 입력하세요: ");
			id = scan.next();
			System.out.printf("비밀번호를 입력하세요: ");
			pwd = scan.next();
			if(!id.equals("admin"))
			{
				System.out.printf("아이디가 틀렸습니다.\n");
				continue;
			}
			if(!pwd.equals("mgr"))
			{
				System.out.printf("비밀번호가 틀렸습니다.\n");
				continue;
			}
			System.out.printf("관리자님 환영합니다.\n");
			pass = 1;
		}
		admin.adminMenu(scan);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Kiosk m = new Kiosk();
		m.run();
	}

}
