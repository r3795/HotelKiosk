package hotel;

public class ReserveDate {
	int [][] dateArr = new int[12][31];
	void init()
	{
		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j < 31; j++)
			{
				if(i==2||i==4||i==6||i==9||i==11)
					if(j==30)
						break;
				dateArr[i][j] = 0;
			}
		}
	}
	void adddate(int month, int date, int period)
	{
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
			dateArr[monthtmp][datetmp] = 1;
			datetmp++;
			num++;
		}
	}
	void deldate(int month, int date, int period)
	{
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
			dateArr[monthtmp][datetmp] = 0;
			datetmp++;
			num++;
		}
	}
	boolean check(int i, int j)
	{
		if(dateArr[i][j] == 1)
			return true;
		return false;
	}
}
