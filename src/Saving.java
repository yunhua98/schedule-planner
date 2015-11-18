import java.io.*;
import java.util.*;
public class Saving {

	// The name of the file to open.
    //private static String fileName;

    // This will reference one line at a time
    //private static String line = null;

    public static Schedule read(String fileName)
    {
    	String line = null;
    	Schedule s = new Schedule(fileName.substring(0, fileName.length() - 4));
    	
    	try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //shit to read from save file and convert to arraylist
            	//System.out.println(line);
            	String className = line.substring(0, line.indexOf("|"));
            	line = line.substring(line.indexOf("|") + 1);
            	String profName = line.substring(0, line.indexOf("|"));
            	line = line.substring(line.indexOf("|") + 1);
            	TimeOfDay startTime = new TimeOfDay(line.substring(0, line.indexOf("|")));
            	line = line.substring(line.indexOf("|") + 1);
            	TimeOfDay endTime = new TimeOfDay(line.substring(0, line.indexOf("|")));
            	line = line.substring(line.indexOf("|") + 1);
            	String daysOfWk = line.substring(0, line.indexOf("|"));
            	line = line.substring(line.indexOf("|") + 1);
            	int creditHours = Integer.parseInt(line);
            	s.addClass(new ClassSection(className, profName, startTime, endTime, daysOfWk, creditHours));
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    	
    	return s;
    	
    }
    
    public static ArrayList<String> getSaveFiles(String directory)
    {
    	ArrayList<String> textFiles = new ArrayList<String>();
    	File dir = new File(directory);
		for (File file : dir.listFiles())
		{
			if (file.getName().toLowerCase().endsWith((".sch")))
			{
			      textFiles.add(file.getName());
			}
		}
		return textFiles;
    }
    
    public static void save(String saveName, Schedule saveSchedule)
    {
    	try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveName + ".sch"), "utf-8")))
    	{
    		for (int i = 0; i < saveSchedule.getNumberOfClasses(); i++)
    		{
    			ClassSection tempClass = saveSchedule.getClassSection(i);
    			writer.write(tempClass.getClassName() + "|");
    			writer.write(tempClass.getProfName() + "|");
    			writer.write(tempClass.getStartTime().getMilitaryTimeString() + "|");
    			writer.write(tempClass.getEndTime().getMilitaryTimeString() + "|");
    			writer.write(tempClass.getDays() + "|");
    			writer.write(tempClass.getCreditHours() + "\n");
    		}
    	} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
