package mgr;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager<T extends Manageable> {
	public ArrayList<T> mList = new ArrayList<>();
	public void readAll(String filename, Factory<T> fac) {
		Scanner filein = openFile(filename);
		T m = null;
		while (filein.hasNext()) {
			m = fac.create();
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}
	public void printAll() {
		for (T m : mList) {
			m.print();
		}
	}
	public T find(String kwd) {
	    for (T m: mList)
	    	if (m.matches(kwd))
	    		return m;
	    return null;
	}
	public void findprint(String kwd) {
	    for (T m: mList)
	    	if (m.matches(kwd))
	    		m.print();
	}
	public List<T> findAll(String kwd) {
		List<T> results = new ArrayList<>();
		for (T m: mList)
			if (m.matches(kwd))
				results.add(m);
		return results;
	}
	public void modify(Scanner keyScanner) {
		String kwd = null;
		while (true) {
			System.out.print("검색할 정보를 입력하세요 (끝낼 시 : end)>> ");
			kwd = keyScanner.next();
			if (kwd.equals("end"))
				break;
			for (T m : mList) {
				if (m.matches(kwd))
				{
					m.modify(keyScanner);
					System.out.println("수정되었습니다.");
				}
			}
		}
	}
	public Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (Exception e) {
			System.out.println(filename + ": 파일 없음");
			System.exit(0);
		}
		return filein;
	}
}