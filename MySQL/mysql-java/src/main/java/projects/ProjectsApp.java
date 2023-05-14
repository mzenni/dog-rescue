package projects;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

/*
 * This class is a menu-driven application that accepts user input from the console. 
 * It performs CRUD operations on the project tables.
 */
@SuppressWarnings("unused")
public class ProjectsApp {
	
	// New project service private instance variable type:
	private ProjectService projectService = new ProjectService(); 
	
	// instance variable type project
	private Project curProject; 
	
	// Display List Menu:
	// @formatter:off
	private List<String> operations = List.of(
			"1.) Add a project",
			"2.) List projects",
			"3.) Select a project"
	);
	// @ formatter:on
	
	// New Scanner Object:
	private Scanner scanner = new Scanner(System.in);
	
	// Main Method: 
	// Entry point for java application
	public static void main(String[] args) {
		 new ProjectsApp().processUserSelections(); 

	}
	
	/*
	 * This method prints the operations, gets a user menu selection, and performs the requested operation
	 * It repeats until the user requests that the application terminate
	 */

	private void processUserSelections() {
		boolean done = false; 
		while (!done) {
			// Gets user selection
			try {
				int selection = getUserSelection();
				
				// Switch statement for user selection 
				switch(selection) {
				// null value
				case -1:
					done = exitMenu(); 
					break;
				case 1:
					createProject();
					break; 
				case 2:
					listProjects(); 
					break;
				case 3:
					selectProject();
					break; 
				default:
					System.out.println("\n" + selection + " is not a valid selection. Please try again.");
;				}
			}
			// Catches error and prints message
			catch(Exception e){
				System.out.println("\nError: " + e );
				
				//e.printStackTrace(); 
			}
		}
	}
	
	private void selectProject() {
		listProjects();
		Integer projectId = getIntInput("Enter a project ID to select a project"); 
		
		/* Unselect the current project. */
		curProject = null; 
		
		/* This will throw an exception if an invalid project ID is entered. */ 
		curProject = projectService.fetchProjectById(projectId); 
		
	}

	/*
	 * Gather user input for a project row then call the project service to create the row. 
	 */

	private void listProjects() {
		List<Project> projects = projectService.fetchAllProjects(); 
		
		System.out.println("\nProjects:"); 
		
		projects.forEach(project -> System.out.println("   " + project.getProjectId() + ":  " + project.getProjectName())); 
	}

	private void createProject() {
		String projectName = getStringInput("Enter the project name"); 
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours"); 
		BigDecimal actualHours = getDecimalInput("Enter the actual hours"); 
		// Instructions don't include validating difficulty input, add it if you have time 
		Integer difficulty = getIntInput("Enter the project difficulty(1-5)"); 
		String notes = getStringInput("Enter the project notes"); 
		
		Project project = new Project();
		
		// Setters
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created project: " + dbProject); 
		
	}
	
	/* 
	 * Gets the user's input from the console and converts it to a BigDecimal.
	 * 
	 * @param prompt The prompt to display on the console
	 * @return A BigDecimal value if successful
	 * @throws DbException  thrown if an error occurs converting the number to a BigDecimal. 
	 */

	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt); 
		
		// Check if input value is null
		if(Objects.isNull(input)) {
			return null; 
		}
		try {
			// Converts string input to integer value
			return new BigDecimal(input).setScale(2); 
		}
		catch(NumberFormatException e){
			// Throws exception when string cannot be converted to an integer value 
			throw new DbException(input + " is not a valid decimal number. Try again. "); 
			
		}
	}
	
	/* 
	 * Called when the user wants to exit the application.
	 * It prints a message and returns (@code True) to terminate the appl.
	 * 
	 * @return {@code true}
	 */
	private boolean exitMenu() {
		System.out.println("Exiting the menu."); 
		return true;
	}
	
	/* 
	 * this method prints the available menu selections.
	 * It then gets the user's menu selection from the console and converts it to an int.
	 * 
	 * @return the menu selection as an int or -1 if nothing is selected 
	 */

	private int getUserSelection() {
		printOperations();
		
		// getIntInput returns user's selection
		Integer input = getIntInput("Enter a menu selection"); 
		
		// return statement that checks if the input is null 
		// if null, returns -1 (exit the method), not null returns input  
		return Objects.isNull(input) ? - 1 : input; 
	}
	
	/* 
	 * Prints a prompt on the console and then gets the user's input from the console.
	 * It then converts the input to an Integer
	 * 
	 * @param prompt the prompt to print
	 * @return If the user enters nothing, {@code null} is returned. Otherwise, the input is converted to an integer
	 * @throws DbException Thrown if the input is not a valid integer
	 */

	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt); 
		
		// Check if input value is null
		if(Objects.isNull(input)) {
			return null; 
		}
		try {
			// Converts string input to integer value
			return Integer.valueOf(input); 
		}
		catch(NumberFormatException e){
			// Throws exception when string cannot be converted to an integer value 
			throw new DbException(input + " is not a valid number. Try again. "); 
			
		}
	}

	/*
	 * Prints a prompt on the console and then gets the user's input from the console.
	 * If the user enters nothing, {@code null} is returned. Otherwise, the trimmed input is returned.
	 * 
	 * @param prompt the prompt to print.
	 * @return the user's input or {@code null} 
	 */
	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String input = scanner.nextLine(); 
		// Test input value (check if null, if not return it trimmed)
		return input.isBlank() ? null : input.trim();
	}

	private void printOperations() {
		System.out.println("These are the available selections. Press the Enter key to quit: "); 
		
		// Prints available selections using lambda expression: 
		operations.forEach(line -> System.out.println("   " + line));
		
		// prints available selections using for loop
		/*for (String line : operations) {
			System.out.println("   " + line); 
		} */
		
		if(Objects.isNull(curProject)) {
			System.out.println("\nYou are not working with a project.");
		}
		else {
			System.out.println("\nYou are working with the project: " + curProject); 
		}
	}
	
	
	

}
