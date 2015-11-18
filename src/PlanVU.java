import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PlanVU {
	
	public static JFrame menuFrame;
	public static JFrame newScheduleFrame;
	public static JFrame viewScheduleFrame;
	public static JFrame addClassFrame;
	public static JFrame successFrame;
	public static JFrame confirmFrame;
	
	public static String destination;
	public static String origin;
	
	public static ArrayList<String> savedSchedules = new ArrayList<String>();
	public static Schedule loadedSchedule = new Schedule("");
	public static boolean isPM;
	public static boolean isPM2;
	public static boolean intention;
	public static boolean confirmed;
		
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		/* SHIT DOESNT WORK, instead switch to each screen being its own method outside of the main method.
		//BEGIN *****CANCELLISTENER*****
		//CancelListener objects make buttons they're added to take
		//the user back to the last page they pressed a navigational button on,
		//the "origin." Other listeners for navigational buttons must modify the
		//"origin" and "destination" static variables so any cancel listener can work.
		class CancelListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event) {
				origin.setVisible(true);
				destination.setVisible(false);
			}
			
		}
		
		//END *****CANCELLISTENER*****
		
		
		//BEGIN UNIVERSAL CANCELBUTTON
		JButton cancelButton = new JButton("Cancel");
		ActionListener cancelListener = new CancelListener();
		cancelButton.addActionListener(cancelListener);
		//END UNIVERSAL CANCELBUTTON
		*/
		
		//BEGIN MAIN MENU SCREEN
		menuFrame = new PlannerFrame();
		JPanel panel = new JPanel();
		panel.add(new Label("Select Saved Schedule:"));
		File file;
		try
		{
			file = new File(PlanVU.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			File fileParent = file.getParentFile();
			savedSchedules = Saving.getSaveFiles(fileParent.getAbsolutePath());
		}
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ActionListener listener;
		JButton b;
		
		//BEGIN SELECTSCHEDULELISTENER*****
		class SelectScheduleListener implements ActionListener {
			
			private String selectedScheduleName;
			
			public SelectScheduleListener(String buttonName)
			{
				selectedScheduleName = buttonName;
			}
			
			public void actionPerformed(ActionEvent event)
			{
				origin = "menuFrame";
				destination = "viewScheduleFrame";
				/* READING SAVE FILES TEST
				System.out.println(scheduleName);
				Saving.read(scheduleName);
				*/
				
				/*READING SAVE FILES TEST2
				Schedule s = Saving.read(scheduleName);
				System.out.println(s.getName() + "\n" + s.getClassSection(0).getClassName() + "\n" + s.getClassSection(1).getProfName() + "\n" + s.getClassSection(1).getClassName());
				*/
				
				//-----INSTANCE 1 OF ASSIGNING A VALUE TO LOADEDSCHEDULE POINTER-----
				loadedSchedule.replaceWith(Saving.read(selectedScheduleName));
				menuFrame.setVisible(false);
				menuFrame.dispose();
				viewScheduleScreen();
				//viewScheduleFrame.setVisible(true);
				//viewScheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		}
		//END SELECTSCHEDULELISTENER*****
		
		
		//BEGIN LOADING SAVED SCHEDULES
		for (String s : savedSchedules)
		{
			b = new JButton(s);
			listener = new SelectScheduleListener(s);
			b.addActionListener(listener);
			panel.add(b);
		}
		//END LOADING SAVED SCHEDULES
		
		
		JButton createNew = new JButton("Create New Schedule");
		//BEGIN NEWSCHEDULELISTENER*****
		class NewScheduleListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event)
			{
				origin = "menuFrame";
				destination = "newScheduleFrame";
				menuFrame.setVisible(false);
				menuFrame.dispose();
				newScheduleScreen();
				//newScheduleFrame.setVisible(true);
				//newScheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		}
		//END NEWSCHEDULELISTENER*****
		
		ActionListener newScheduleListener = new NewScheduleListener();
		createNew.addActionListener(newScheduleListener);
		panel.add(createNew);
		menuFrame.add(panel);
		menuFrame.setTitle("PlanVU");
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setVisible(true);
		//END MAIN MENU SCREEN
		
		
		
		
		//BEGIN VIEW SCHEDULE SCREEN
		
	/*	viewScheduleFrame = new PlannerFrame();
		JPanel viewSchedulePanel = new JPanel();
		JLabel loadedScheduleNameLabel = new JLabel("Schedule Name: " + loadedSchedule.getName());
		viewSchedulePanel.add(loadedScheduleNameLabel);
		viewSchedulePanel.add(cancelButton);
		viewScheduleFrame.add(viewSchedulePanel);
		viewScheduleFrame.setVisible(false);
		//System.out.println("TEST: " + loadedSchedule.getName());
		viewScheduleFrame.setTitle("PlanVU");
		viewScheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	*/	
		//END VIEW SCHEDULE SCREEN
		
		
		
		
		/* - TEST SAVE -
		ClassSection orgo = new ClassSection("Orgo", "Rizzo", new TimeOfDay(10, 10, false), new TimeOfDay(11, 0, false), "MWF", 3);
		Schedule saveSchedule = new Schedule("TEST3");
		ClassSection math = new ClassSection("MVC", "Falgas", new TimeOfDay(1, 10, true), new TimeOfDay(2, 0, true), "MWF", 3);
		saveSchedule.addClass(math);
		saveSchedule.addClass(orgo);
		Saving.save("TEST3", saveSchedule);
		- END -*/
		
		/* - TEST READ -
	    File file;
		try {
			file = new File(PlannerGUI.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			File fileParent = file.getParentFile();
			System.out.println(Saving.getSaveFiles(fileParent.getAbsolutePath()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		Saving.save("TEST2");
		- END -*/
		
	}
	
	public static void menuScreen()
	{
		//BEGIN MAIN MENU SCREEN
				menuFrame = new PlannerFrame();
				JPanel panel = new JPanel();
				panel.add(new Label("Select Saved Schedule:"));
				File file;
				ArrayList<String> savedSchedules = new ArrayList<String>();
				try
				{
					file = new File(PlanVU.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
					File fileParent = file.getParentFile();
					savedSchedules = Saving.getSaveFiles(fileParent.getAbsolutePath());
				}
				catch (URISyntaxException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ActionListener listener;
				JButton b;
				
				//BEGIN SELECTSCHEDULELISTENER*****
				class SelectScheduleListener implements ActionListener {
					
					private String selectedScheduleName;
					
					public SelectScheduleListener(String buttonName)
					{
						selectedScheduleName = buttonName;
					}
					
					public void actionPerformed(ActionEvent event)
					{
						origin = "menuFrame";
						destination = "viewScheduleFrame";
						/* READING SAVE FILES TEST
						System.out.println(scheduleName);
						Saving.read(scheduleName);
						*/
						
						/*READING SAVE FILES TEST2
						Schedule s = Saving.read(scheduleName);
						System.out.println(s.getName() + "\n" + s.getClassSection(0).getClassName() + "\n" + s.getClassSection(1).getProfName() + "\n" + s.getClassSection(1).getClassName());
						*/
						
						//-----INSTANCE 1 OF ASSIGNING A VALUE TO LOADEDSCHEDULE POINTER-----
						loadedSchedule.replaceWith(Saving.read(selectedScheduleName));
						menuFrame.setVisible(false);
						menuFrame.dispose();
						viewScheduleScreen();
						//viewScheduleFrame.setVisible(true);
						//viewScheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}

				}
				//END SELECTSCHEDULELISTENER*****
				
				
				//BEGIN LOADING SAVED SCHEDULES
				for (String s : savedSchedules)
				{
					b = new JButton(s);
					listener = new SelectScheduleListener(s);
					b.addActionListener(listener);
					panel.add(b);
				}
				//END LOADING SAVED SCHEDULES
				
				
				JButton createNew = new JButton("Create New Schedule");
				//BEGIN NEWSCHEDULELISTENER*****
				class NewScheduleListener implements ActionListener {
					
					public void actionPerformed(ActionEvent event)
					{
						origin = "menuFrame";
						destination = "newScheduleFrame";
						menuFrame.setVisible(false);
						menuFrame.dispose();
						newScheduleScreen();
						//newScheduleFrame.setVisible(true);
						//newScheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}

				}
				//END NEWSCHEDULELISTENER*****
				
				ActionListener newScheduleListener = new NewScheduleListener();
				createNew.addActionListener(newScheduleListener);
				panel.add(createNew);
				menuFrame.add(panel);
				menuFrame.setTitle("PlanVU");
				menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				menuFrame.setVisible(true);
				//END MAIN MENU SCREEN
	}
	
	
	public static void viewScheduleScreen()
	{
		viewScheduleFrame = new PlannerFrame();
		JPanel viewSchedulePanel = new JPanel();
		viewSchedulePanel.setLayout(new BoxLayout(viewSchedulePanel, BoxLayout.Y_AXIS));
		JLabel loadedScheduleNameLabel = new JLabel("<html>Schedule Name: " + loadedSchedule.getName() + "</html>");
		viewSchedulePanel.add(loadedScheduleNameLabel);
		String scheduleDisplay = "<html>";
		ArrayList<ClassSection> classList = loadedSchedule.getClassArrayList();
		for (ClassSection cs : classList)
		{
			scheduleDisplay += "<br>Class Name: " + cs.getClassName() + "<br>Professor Name: " + cs.getProfName() + "<br>Start Time: " + cs.getStartTime().toString() + "<br>End Time: " + cs.getEndTime().toString() + "<br>Meeting Days: " + cs.getDays() + "<br>Credit Hours: " + cs.getCreditHours() + "<br>";
		}
		scheduleDisplay += "</html>";
		JLabel scheduleDisplayLabel = new JLabel(scheduleDisplay);
		viewSchedulePanel.add(scheduleDisplayLabel);
		
		JButton backButton = new JButton("Back");
		
		class BackButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if (origin.equals("menuFrame"))
				{
					viewScheduleFrame.setVisible(false);
					viewScheduleFrame.dispose();
					menuScreen();
				}
				if (origin.equals("newScheduleFrame"))
				{
					newScheduleFrame.setVisible(true);
					viewScheduleFrame.setVisible(false);
					viewScheduleFrame.dispose();
				}
			}
		}
		
		ActionListener backButtonListener = new BackButtonListener();
		backButton.addActionListener(backButtonListener);
		
		//BEGIN ADD CLASS BUTTON
		JButton addClassButton = new JButton("Add a Class");
		
		class AddClassListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				viewScheduleFrame.setVisible(false);
				viewScheduleFrame.dispose();
				addClassScreen();
			}
		}
		
		ActionListener addClassListener = new AddClassListener();
		addClassButton.addActionListener(addClassListener);
		//END ADD CLASS BUTTON
		
		viewSchedulePanel.add(addClassButton);
		viewSchedulePanel.add(backButton);
		
		viewScheduleFrame.add(viewSchedulePanel);
		viewScheduleFrame.setVisible(true);
		//System.out.println("TEST: " + loadedSchedule.getName());
		viewScheduleFrame.setTitle("PlanVU");
		viewScheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void newScheduleScreen()
	{
		//BEGIN NEW SCHEDULE SCREEN
		
		newScheduleFrame = new PlannerFrame();
		JPanel newSchedulePanel = new JPanel();
		final int FIELD_WIDTH = 10;
		final JTextField scheduleNameField = new JTextField(FIELD_WIDTH);
		JLabel scheduleNameLabel = new JLabel("New Schedule Name: ");
		scheduleNameField.setText("My Schedule");
				
		JButton nextButton = new JButton("Next");
		//BEGIN NEXTLISTENER	
		class nextListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				String scheduleName = scheduleNameField.getText();
				
				boolean uniqueName = true;
				for (String scheduleNames : savedSchedules)
				{
					if (scheduleNames.equalsIgnoreCase(scheduleName + ".sch"))
						uniqueName = false;
				}
				
				if (uniqueName)
				{
					//-----INSTANCE 2 OF ASSIGNING VALUE TO LOADEDSCHEDULE POINTER-----
					Schedule newTempSchedule = new Schedule(scheduleName);
					loadedSchedule.replaceWith(newTempSchedule);
					//System.out.println(scheduleName);
					origin = "newScheduleFrame";
					destination = "viewScheduleFrame";
					newScheduleFrame.setVisible(false);
					newScheduleFrame.dispose();
					viewScheduleScreen();
					//OLDSYSTEMviewScheduleFrame.setVisible(true);
					//OLDSYSTEMviewScheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
				else
				{
					newScheduleFrame.setVisible(false);
					newScheduleFrame.dispose();
					confirmOverwriteScreen(scheduleName);
					
					/*while (!confirmed)
					{
						
					}
					confirmed = false;*/
					
					/*if (intention)
					{
						//-----INSTANCE 2.5 OF ASSIGNING VALUE TO LOADEDSCHEDULE POINTER-----
						Schedule newTempSchedule = new Schedule(scheduleName);
						loadedSchedule.replaceWith(newTempSchedule);
						origin = "newScheduleFrame";
						destination = "viewScheduleFrame";
						newScheduleFrame.setVisible(false);
						newScheduleFrame.dispose();
						viewScheduleScreen();
					}
					else
					{
						newScheduleFrame.setVisible(false);
						newScheduleFrame.dispose();
						newScheduleScreen();
					}*/
				}
				
			}
		}
		//END NEXTLISTENER
		ActionListener nextListener = new nextListener();
		nextButton.addActionListener(nextListener);
		
		class CancelListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				newScheduleFrame.setVisible(false);
				newScheduleFrame.dispose();
				//menuFrame.setVisible(true);
				//menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				menuScreen();
			}
		}
		
		ActionListener cancelListener = new CancelListener();
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(cancelListener);
		
		newSchedulePanel.add(scheduleNameLabel);
		newSchedulePanel.add(scheduleNameField);
		newSchedulePanel.add(nextButton);
		newSchedulePanel.add(cancelButton);
		newScheduleFrame.add(newSchedulePanel);
		newScheduleFrame.setVisible(true);
		newScheduleFrame.setTitle("PlanVU");
		newScheduleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//END NEW SCHEDULE SCREEN
	}
	
	public static void addClassScreen()
	{
		addClassFrame = new PlannerFrame();
		JPanel addClassPanel = new JPanel();
		
		//Add new Class fields
		final int FIELD_WIDTH = 10;
		JLabel classNameLabel = new JLabel("Class Name: ");
		final JTextField classNameField = new JTextField(FIELD_WIDTH);
		JLabel profNameLabel = new JLabel("\nProfessor Name: ");
		final JTextField profNameField = new JTextField(FIELD_WIDTH);
		JLabel startTimeLabel = new JLabel("\nStart Time: ");
		final JTextField startHourField = new JTextField(2);
		JLabel startTimeDivider = new JLabel(":");
		final JTextField startMinuteField = new JTextField(2);
		
		//Begin AMPMSelector
		String[] choiceList = { "AM", "PM" };
		JComboBox selectorAMPM = new JComboBox(choiceList);
		selectorAMPM.setSelectedIndex(0);
		isPM = false;
		
		class SelectorListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				JComboBox cbTemp = (JComboBox) event.getSource();
		        String choice = (String) cbTemp.getSelectedItem();
		        if (choice.equals("PM"))
		        	isPM = true;
		        else
		        	isPM = false;
			}
		}
		
		
		ActionListener selectorListener = new SelectorListener();
		selectorAMPM.addActionListener(selectorListener);
		//End AMPMSelector

		
		JLabel endTimeLabel = new JLabel("\nEnd Time: ");
		final JTextField endHourField = new JTextField(2);
		JLabel endTimeDivider = new JLabel(":");
		final JTextField endMinuteField = new JTextField(2);
		
		//Begin AMPMSelector2
		String[] choiceList2 = { "AM", "PM" };
		JComboBox selectorAMPM2 = new JComboBox(choiceList2);
		selectorAMPM2.setSelectedIndex(0);
		isPM2 = false;
		
		class SelectorListener2 implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				JComboBox cbTemp = (JComboBox) event.getSource();
		        String choice = (String) cbTemp.getSelectedItem();
		        if (choice.equals("PM"))
		        	isPM2 = true;
		        else
		        	isPM2 = false;
			}
		}
		
		
		ActionListener selectorListener2 = new SelectorListener2();
		selectorAMPM2.addActionListener(selectorListener2);
		//End AMPMSelector2

		
		JLabel daysOfWkLabel = new JLabel("\nClass Meeting Days: ");
		final JTextField daysOfWkField = new JTextField(4);
		JLabel creditHoursLabel = new JLabel("\nCredit Hours: ");
		final JTextField creditHoursField = new JTextField(3);		
		
		//Begin Submit
		
		class ClassSubmitListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				TimeOfDay startTime = new TimeOfDay(Integer.parseInt(startHourField.getText()), Integer.parseInt(startMinuteField.getText()), isPM);
				TimeOfDay endTime = new TimeOfDay(Integer.parseInt(endHourField.getText()), Integer.parseInt(endMinuteField.getText()), isPM2);
				ClassSection newClass = new ClassSection(classNameField.getText(), profNameField.getText(), startTime, endTime, daysOfWkField.getText(), Integer.parseInt(creditHoursField.getText()));
				loadedSchedule.addClass(newClass);
				Saving.save(loadedSchedule.getName(), loadedSchedule);
				successfullyAddedClass();
			}
		}
		
		
		ActionListener classSubmitListener = new ClassSubmitListener();
		JButton submitClassButton = new JButton("Submit");
		submitClassButton.addActionListener(classSubmitListener);
		//End Submit

		
		
		addClassPanel.add(classNameLabel);
		addClassPanel.add(classNameField);
		addClassPanel.add(profNameLabel);
		addClassPanel.add(profNameField);
		addClassPanel.add(startTimeLabel);
		addClassPanel.add(startHourField);
		addClassPanel.add(startTimeDivider);
		addClassPanel.add(startMinuteField);
		addClassPanel.add(selectorAMPM);
		addClassPanel.add(endTimeLabel);
		addClassPanel.add(endHourField);
		addClassPanel.add(endTimeDivider);
		addClassPanel.add(endMinuteField);
		addClassPanel.add(selectorAMPM2);
		addClassPanel.add(daysOfWkLabel);
		addClassPanel.add(daysOfWkField);
		addClassPanel.add(creditHoursLabel);
		addClassPanel.add(creditHoursField);
		addClassPanel.add(submitClassButton);
		
		//End Add new Class fields
		
		//Cancel Button
		JButton cancelButton = new JButton("Cancel");
		
		class CancelButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				addClassFrame.setVisible(false);
				addClassFrame.dispose();
				viewScheduleScreen();
			}
		}
		
		ActionListener cancelButtonListener = new CancelButtonListener();
		cancelButton.addActionListener(cancelButtonListener);
		addClassPanel.add(cancelButton);
		//End Cancel Button
		
		addClassFrame.add(addClassPanel);
		addClassFrame.setVisible(true);
		addClassFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void successfullyAddedClass()
	{
		successFrame = new DialogueFrame();
		JLabel successLabel = new JLabel("Successfully Added Class: " + loadedSchedule.getClassSection(loadedSchedule.getNumberOfClasses() - 1).getClassName());
		JPanel successPanel = new JPanel();
		successPanel.add(successLabel);
		JButton okButton = new JButton("OK");
		
		class OKListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				addClassFrame.setVisible(false);
				addClassFrame.dispose();
				successFrame.setVisible(false);
				successFrame.dispose();
				viewScheduleScreen();
			}
		}
		
		ActionListener okListener = new OKListener();
		okButton.addActionListener(okListener);
		successPanel.add(okButton);
		successFrame.add(successPanel);
		successFrame.setVisible(true);
		successFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void confirmOverwriteScreen(String scheduleName)
	{
		confirmFrame = new DialogueFrame();
		JPanel confirmPanel = new JPanel();
		JLabel confirmLabel = new JLabel("This schedule already exists. Are you sure you wish to overwrite?");
		JButton yesButton = new JButton("Yes");
		JButton noButton = new JButton("No");
		class YesNoListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				confirmed = true;
				JButton tempButton = (JButton) event.getSource();
				if (tempButton.getText().equals("Yes"))
					intention = true;
				else
					intention = false;
				confirmFrame.setVisible(false);
				confirmFrame.dispose();
				
				if (intention)
				{
					//-----INSTANCE 2.5 OF ASSIGNING VALUE TO LOADEDSCHEDULE POINTER-----
					Schedule newTempSchedule = new Schedule(scheduleName);
					loadedSchedule.replaceWith(newTempSchedule);
					origin = "newScheduleFrame";
					destination = "viewScheduleFrame";
					confirmFrame.setVisible(false);
					confirmFrame.dispose();
					viewScheduleScreen();
				}
				else
				{
					confirmFrame.setVisible(false);
					confirmFrame.dispose();
					newScheduleScreen();
				}
			}
		}
		ActionListener yesNoListener = new YesNoListener();
		yesButton.addActionListener(yesNoListener);
		noButton.addActionListener(yesNoListener);
		confirmPanel.add(confirmLabel);
		confirmPanel.add(yesButton);
		confirmPanel.add(noButton);
		confirmFrame.add(confirmPanel);
		confirmFrame.setVisible(true);
		confirmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
			
	
}
