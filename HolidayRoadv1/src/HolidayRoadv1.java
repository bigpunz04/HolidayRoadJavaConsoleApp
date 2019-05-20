
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.*;

public class HolidayRoadv1 {

	public static void main(String[] args) {
		
		//CREATE MYSQL CONNECTION
		MySQL2 sqlConn = new MySQL2();
		
		//GET EMPLOYEE DIRECTORY
		String empDirectory = "SELECT * FROM v2hrEmployees";
		empLL employeeDirectory = sqlConn.getDirectory(empDirectory);
		
		//CREATE DATE OBJECT AND GET CURRENT DATE
		LocalDate localDate = LocalDate.now();
		String currentDate = DateTimeFormatter.ofPattern("MM-dd-yyyy").format(localDate) + "";
		
		//SQL CONNECT STATUS
		System.out.println("MySQL Connection Succesful: " + currentDate);
		System.out.println("HolidayRoad v1.0");
		System.out.println("By: Rhein Punzalan");
		System.out.println();
		
		//WELCOME MESSAGE AND EMPLOYEE CREATION
		System.out.println("************************************************");
		System.out.println("Welcome to HOLIDAY ROAD - Where Time Off Begins!");
		System.out.println("************************************************");
		System.out.println();
		System.out.println();
		System.out.println("*********************************************************************");
		System.out.println("Enter your 4 Digit Employee ID or enter a '-1' to view the directory.");
		System.out.println();
		int id = getNumInput();
		System.out.println("*********************************************************************");
		System.out.println();
		System.out.println();	
		
		//CREATE A NEW EMPLOYEE OBJECT AND SET IT TO NULL.
		//THIS OBJECT WILL BE POPULATED WITH THE OBJECT DETAILS IN THE WHILE LOOP
		Employee employee = null;
		
		//This will be used in the main() only
		
		
		boolean empFound = false;
		while(!empFound) {
			
			if(id == -1) {
				System.out.println();
				System.out.println("EMPLOYEE DIRECTORY");
				System.out.println();
				employeeDirectory.displayList();
				System.out.println("If you do not see your Employee ID, you will need to be added by an employee with a title of 'Manager'.");
				System.out.println();
				id = getNumInput();
			}else if(employeeDirectory.contains(id)){
				//check if the id is in the db.
				//check what type of employee object to create (manager, full time, part time)
				empNode empInfo = employeeDirectory.getEmpInfo(id);
				if(empInfo.getEmpTitle().contains("Manager")) {
					employee = new FullTimeManager(empInfo, sqlConn, employeeDirectory);
				}else if(empInfo.getEmpType() == 'f' || empInfo.getEmpType() == 'F') {
					employee = new FullTimeEmployee(empInfo, sqlConn);
				}else employee = new PartTimeEmployee(empInfo, sqlConn);
				empFound = true;
			}else{
				System.out.println("**********************************************");
				System.out.println("ID not found. Please try again.");
				id = getNumInput();
				System.out.println("**********************************************");
				System.out.println();
			}
			System.out.println();
		}
		
		System.out.println("*********************************************************************");
		System.out.println("Hello, " + employee.getEmpFirst() + " " + employee.getEmpLast() + "!");
		System.out.println("How are things going in the " + employee.getEmpDepartment() + " department?");
		System.out.println();
		System.out.println("What can I do for you today? ");
		System.out.println();
		
		boolean resume = true;
		while(resume){
			//PULL UP THE MAIN MENU BASED ON THE EMPLOYEE TYPE
			employee.mainMenu();
			int menu_select = getNumInput();
			resume = employee.mainMenuSelect(menu_select);
			
		}
	
		
		
		System.out.println();
		System.out.println();
		System.out.println("********************************");
		System.out.println("Thank you for using Holiday Road");
		System.out.println("Goodbye!");
		System.out.println("********************************");
		
	}
	
	
	//CHECK IF THE ENTERED VALUE IS A NUMBER
	public static int getNumInput() {
		Scanner input = new Scanner(System.in);
		int userInput;
		while(true) {
			try {
				System.out.println();
				System.out.print("Selection: ");
				userInput = input.nextInt();
				return userInput;
			}
			catch(InputMismatchException e) {
				System.out.println("You did not enter a number.  Please try again.");
				input.next();
			}
		}
	
	}

	}	
