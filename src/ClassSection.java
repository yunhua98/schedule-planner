
public class ClassSection {

	private String className;
	private String profName;
	private TimeOfDay startTime;
	private TimeOfDay endTime;
	private String daysOfWk;
	private int creditHours;
	
	public ClassSection(String className, String profName, TimeOfDay startTime, TimeOfDay endTime, String daysOfWk, int creditHours)
	{
		this.className = className;
		this.profName = profName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.daysOfWk = daysOfWk;
		this.creditHours = creditHours;
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public String getProfName()
	{
		return profName;
	}
	
	public TimeOfDay getStartTime()
	{
		return startTime;
	}
	
	public TimeOfDay getEndTime()
	{
		return endTime;
	}
	
	public String getDays()
	{
		return daysOfWk;
	}
	
	public int getCreditHours()
	{
		return creditHours;
	}
}
