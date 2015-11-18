
public class TimeOfDay {

	private int hours;
	private int minutes;
	private boolean isPM;
	private int militaryTime;
	private String militaryTimeString;
	
	public TimeOfDay(int hours, int minutes, boolean isPM)
	{
		this.hours = hours;
		this.minutes = minutes;
		this.isPM = isPM;
		if (minutes < 10)
		{
			if (hours == 12)
				militaryTimeString = "00" + minutes;
			militaryTimeString = hours + "0" + minutes;
		}
		else if (hours == 12)
			militaryTimeString = "00" + minutes;
		else
			militaryTimeString = hours + "" + minutes;
		militaryTime = Integer.parseInt(militaryTimeString);
		if (isPM)
			militaryTime += 1200;
	}
	
	public TimeOfDay(String militaryTimeString)
	{
		this.militaryTimeString = militaryTimeString;
		militaryTime = Integer.parseInt(militaryTimeString);
		minutes = militaryTime % 100;
		isPM = true;
		if (militaryTime / 100 < 12)
			isPM = false;
		else if (militaryTime / 100 > 12)
			militaryTime -= 1200;
		else if (militaryTime / 100 == 0)
			militaryTime = 1200;
		hours = militaryTime / 100;
		militaryTime = Integer.parseInt(militaryTimeString);
	}
	
	public int getHours()
	{
		return hours;
	}
	
	public int getMinutes()
	{
		return minutes;
	}
	
	public boolean getIsPM()
	{
		return isPM;
	}
	
	public int getMilitaryTime()
	{
		return militaryTime;
	}
	
	public String getMilitaryTimeString()
	{
		return militaryTimeString;
	}
	
	public boolean isBefore(TimeOfDay time)
	{
		return (militaryTime < time.getMilitaryTime());
	}
	
	public String toString()
	{
		if (isPM)
			return getHours() + ":" + getMinutes() + "PM";
		else
			return getHours() + ":" + getMinutes() + "AM";
	}
	
}
