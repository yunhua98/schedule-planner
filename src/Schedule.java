import java.util.ArrayList;
public class Schedule {

	private String name;
	private ArrayList<ClassSection> classList;
	
	public Schedule(String scheduleName)
	{
		name = scheduleName;
		classList = new ArrayList<ClassSection>();
	}
	
	public void replaceWith(Schedule newSchedule)
	{
		classList = newSchedule.getClassArrayList(); 
		name = newSchedule.getName();
	}
	
	public ArrayList<ClassSection> getClassArrayList()
	{
		return classList;
	}
	
	public ClassSection getClassSection(int index)
	{
		return classList.get(index);
	}
	
	public boolean addClass(ClassSection section)
	{
		boolean isAdded = true;
		for (ClassSection tempClass : classList)
			{
				if ((section.getEndTime().isBefore(tempClass.getEndTime()) && !section.getEndTime().isBefore(tempClass.getStartTime())) || (section.getStartTime().isBefore(tempClass.getEndTime()) && !section.getStartTime().isBefore(tempClass.getStartTime())))
					isAdded = false;
			}
		if (isAdded)
			classList.add(section);
		return isAdded;
	}
	
	public int getNumberOfClasses()
	{
		return classList.size();
	}
	
	public int getTotalHours()
	{
		int totalHours = 0;
		for (ClassSection c : classList)
			totalHours += c.getCreditHours();
		return totalHours;
	}
	
	public String getName()
	{
		return name;
	}
}
