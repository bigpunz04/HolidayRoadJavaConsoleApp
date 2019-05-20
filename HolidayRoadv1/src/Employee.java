import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.DateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee {
	private int empID;
	private String empPassword;
	private String empFirst;
	private String empLast;
	private String empDepartment;
	private String empTitle;
	private char empType;
	private double empVacationHours;
	private MySQL2 connection;
	
	private String today;
	DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
	
	public Employee(empNode emp, MySQL2 sqlConn) {
		this.empID = emp.getEmpID();
		this.empPassword = emp.getEmpPassword();
		this.empFirst = emp.getEmpFirst();
		this.empLast = emp.getEmpLast();
		this.empDepartment = emp.getEmpDepartment();
		this.empTitle = emp.getEmpTitle();
		this.empType = emp.getEmpType();
		this.empVacationHours = emp.getEmpVacationHours();
		  
		this.connection = sqlConn;
		
        //DateFormat df instance variable.  Get current date.
        Date date = new Date();
        this.today= this.df.format(date);
	}
	
	public boolean mainMenuSelect(int select) {
		Scanner input = new Scanner(System.in);
		if(select == -1) {
			return false;
		}else if(select == 1) {
			quickTimeOffReport();
			timeOffMenu();
			int timeOffSelect = getNumInput(input);
			submitTime(timeOffSelect);
			if(timeOffSelect == -1) {
				return false;
			}
		}else if(select == 2) {
			viewHistoryMenu();
			int filterSelect = getNumInput(input);
			viewHistory(filterSelect);
			if(filterSelect == -1) {
				return false;
			}
		}else System.out.println("Invalid entry.  Please try again.");
		return true;
	}
	//USED FOR FULL TIME EMPLOYEE AND FULL TIME MANAGERS
	public void viewHistory(int select) {
		Scanner input = new Scanner(System.in);
		if(select == 1) {
			System.out.println("You have selected to view history based on a date range");
			System.out.println();
			String startDate = getStartDate("history");
			System.out.println();
			String endDate = getEndDate("history");
			
			timeOffLL dateList = createSelectRangeSQLStmt(startDate, endDate);
			System.out.println();
			System.out.println("ALL REQUISITION MADE BETWEEN " + startDate + " AND " + endDate);
			System.out.println();
			dateList.displayList();
		}else if(select == 2) {
			System.out.println("You have selected to view history based on status");
			System.out.println();
			System.out.println("STATUS MENU");
			System.out.println();
			System.out.println("  1: Pending");
			System.out.println("  2. Approved");
			System.out.println("  3: Declined");
			System.out.println();
			System.out.println(" -1: Exit Menu");
			System.out.println();
			int statusSelection = getNumInput(input);
			String type ="";
			timeOffLL statusList = createSelectStatusSQLStmt(statusSelection);
			statusList.displayList();
		}else if(select == 3) {
			System.out.println();
			System.out.println("You have selected to view history based on type");
			System.out.println();
			System.out.println("TYPE MENU");
			System.out.println();
			System.out.println("  1: Vacation Requests");
			System.out.println("  2: Floating Holiday Requests");
			System.out.println("  3: Sick Requests");
			System.out.println("  4: Unpaid Requests");
			System.out.println("  5: Bereavement Requests");
			System.out.println();
			System.out.println(" -1: Exit Menu");
			System.out.println();
			int typeSelect = getNumInput(input);
			String type ="";
			timeOffLL typeList = createSelectTypeSQLStmt(typeSelect);
			typeList.displayList();
		}
	}
	
	public timeOffLL createSelectTypeSQLStmt(int status) {
		timeOffLL newList = null;
		if(status ==1) {
			System.out.println();
			System.out.println("ALL VACATIONS REQUISITIONS");
			String s = "SELECT * FROM `timeOffRequestsView` WHERE empID =" + this.empID + " AND timeOffTypeId = 1";
			newList = this.connection.getRequisitionsByDate(s);
		}else if(status == 2) {
			System.out.println();
			System.out.println("ALL FLOATING HOLIDAY REQUISITIONS");
			String s = "SELECT * FROM `timeOffRequestsView` WHERE empID =" + this.empID + " AND timeOffTypeId = 2";
			newList = this.connection.getRequisitionsByDate(s);
		}else if(status == 3) {
			System.out.println();
			System.out.println("ALL SICK REQUISITIONS");
			String s = "SELECT * FROM `timeOffRequestsView` WHERE empID =" + this.empID + " AND timeOffTypeId = 3";
			newList = this.connection.getRequisitionsByDate(s);
		}else if(status == 4) {
			System.out.println();
			System.out.println("ALL UNPAID REQUISITIONS");
			String s = "SELECT * FROM `timeOffRequestsView` WHERE empID =" + this.empID + " AND timeOffTypeId = 4 ";
			newList = this.connection.getRequisitionsByDate(s);
		}else if(status == 5) {
			System.out.println();
			System.out.println("ALL BEREAVEMENT REQUISITIONS");
			String s = "SELECT * FROM `timeOffRequestsView` WHERE empID =" + this.empID + " AND timeOffTypeId = 5";
			newList = this.connection.getRequisitionsByDate(s);
		}else System.out.println("Invalid selection");
		return newList;
	}
	
	public timeOffLL createSelectStatusSQLStmt(int status) {
		timeOffLL newList = null;
		if(status == 1) {
			System.out.println("ALL REQUISITIONS WITH A STATUS OF PENDING");
			String s = "SELECT * FROM `timeOffRequestsView` WHERE empID =" + this.empID + " AND (decision = 'p' OR decision = 'P')";
			newList = this.connection.getRequisitionsByDate(s);
		}else if(status == 2) {
			System.out.println("ALL REQUISITIONS WITH A STATUS OF APPROVED");
			String s = "SELECT * FROM `timeOffRequestsView` WHERE empID =" + this.empID + " AND (decision = 'a' OR decision = 'A')";
			newList = this.connection.getRequisitionsByDate(s);
		}else if(status == 3) {
			System.out.println("ALL REQUISITIONS WITH A STATUS OF DECLINE");
			String s = "SELECT * FROM `timeOffRequestsView` WHERE empID =" + this.empID + " AND (decision = 'd' OR decision = 'D')";
			newList = this.connection.getRequisitionsByDate(s);
		}
		return newList;
	}
	public timeOffLL createSelectRangeSQLStmt(String date1, String date2) {
		String s = "SELECT * FROM timeOffRequestsView WHERE (dateRequested >= '" + date1 + "' AND dateRequested <= '" + date2 + "') AND empID = " + this.empID + " ORDER BY dateRequested;";
		timeOffLL newList = this.connection.getRequisitionsByDate(s);
		return newList;
	}

	//USED FOR FULL TIME EMPLOYEE AND FULL TIME MANAGER
	public void submitTime(int select) {
		Scanner input = new Scanner(System.in);
			//VACATION TIME
			if(select == 1) {
			System.out.println("You have selected to submit a Vacation Time Request");
			System.out.println();
			String startDate = getStartDate("vacation");
			System.out.println();
			String endDate = getEndDate("vacation");
			System.out.println();
			int startEndInHours = dateDiff(startDate, endDate) * 8;
			int timeUsed = getTimeUsed(select);
			if((getVacationTime() - timeUsed) > startEndInHours) {
				System.out.println("If this vacation is approved, you will be left with " + (this.getVacationTime() - timeUsed - startEndInHours) + " hours.");
				String note = getNote(input);
				createInsertSQLStmt(this.today, select, startDate, endDate, startEndInHours, note);
				submittedMessage();
			}else System.out.println("You do not have enough vacation time for the date range you have entered");
			
			//FLOATING HOLIDAY
			}else if(select == 2) {
				System.out.println("You have selected to submit a Floating Holiday Request");
				System.out.println();
				String startDate = getStartDate("floating holiday");
				System.out.println();
				String endDate = getEndDate("floating holiday");
				System.out.println();
				int startEndInHours = dateDiff(startDate, endDate) * 8;
				int timeUsed = getTimeUsed(select);
				if(getFloatingHolidayTime() - timeUsed > startEndInHours) {
					System.out.println("If this floating holiday request is approved, you will be left with " + (getFloatingHolidayTime() - timeUsed - startEndInHours) + " hours.");
					String note = getNote(input);
					createInsertSQLStmt(this.today, select, startDate, endDate, startEndInHours, note);
					submittedMessage();
				}else System.out.println("You do not have enough floating holiday time for the date range you entered");
				
			//SICK TIME
			}else if(select == 3) {
				System.out.println("You have selected to submit a Sick Request");
				System.out.println();
				String endDate = getEndDate("sick");
				System.out.println();
				int startEndInHours = dateDiff(this.today, endDate) * 8;
				int timeUsed = getTimeUsed(select);
				if(getSickTime() - timeUsed > startEndInHours) {
					System.out.println("If this sick time request is approved, you will be left with " + (getSickTime()- timeUsed - startEndInHours) + " hours.");
					String note = getNote(input);
					createInsertSQLStmt(this.today, select, this.today, endDate, startEndInHours, note);
					submittedMessage();
				}else System.out.println("You do not have enough sick time for the dates range you enetered");
			
			//UNPAID TIME
			}else if(select == 4) {
				System.out.println("You have selected to submit an Unpaid Request");
				System.out.println();
				String startDate = getStartDate("unpaid");
				System.out.println();
				String endDate = getEndDate("unpaid");
				System.out.println();
				int startEndInHours = dateDiff(startDate, endDate) * 8;
				System.out.println("You are submitting an unpaid time request for a total of " + startEndInHours + " hours.");
				String note = getNote(input);
				createInsertSQLStmt(this.today, select, startDate, endDate, startEndInHours, note);
				submittedMessage();
				
			//BEREAVEMENT TIME
			}else if(select == 5) {
				System.out.println("You have selected to submit a Bereavement Request");
				System.out.println();
				String startDate = getStartDate("bereavement");
				System.out.println();
				String endDate = getEndDate("bereavement");
				System.out.println();
				int startEndInHours = dateDiff(startDate, endDate) *8;
				System.out.println("You are submitting a bereavement time request for a total of "  + startEndInHours + " hours.");
				String note = getNote(input);
				createInsertSQLStmt(this.today, select, startDate, endDate, startEndInHours, note);
				submittedMessage();
				
			}else System.out.println("You have made an invalid choice");
	}
	
	public void submittedMessage() {
		System.out.println("Your request has been sent.  Please allow some time for your manager to review");
	}
	
	public String getNote(Scanner input) {
		System.out.println();
		System.out.print("Enter a note about your time off request: ");
		String note = input.nextLine();
		return note;
	}
	
	public void createInsertSQLStmt(String today, int timeOffTypeId, String startDate, String endDate, int totalHours, String note ) {
		String s = "Insert into v2hrTimeOffRequests(dateRequested, empID, timeOffTypeId, startDate, endDate, totalHoursOff, empNote) VALUES ( '" + today + "', " + this.empID + ", " + timeOffTypeId + ", '" + startDate + "', '" + endDate + "', " + totalHours + ", '" + note + "')";
		this.connection.addRequest(s);
	}
	public String getStartDate(String type) {
		System.out.print("Please enter the " + type + " start date(mm-dd-yyyy): ");
		String start = dateCheck();
		return start;
	}
	
	public String getEndDate(String type) {
		System.out.print("Please enter the " + type + " end date(mm-dd-yyyy): ");
		String end = dateCheck();
		return end;
	}
	
	//called from submitTime()
	//Acquires dates from user and calls datecheck() to checks if the string entered is in the correct form
	public int startEndTimeOff(int selection, String s) {
		
		System.out.println("Please enter the " + s + " start date (mm-dd-yyyy): ");
		String start = dateCheck();
		System.out.println("Please enter the " + s + " end date (mm-dd-yyyy): ");
		String end = dateCheck();
		
		
		int difference = dateDiff(start, end);
		return difference;
	}
	
	//called from startEndTimeOff()
	//get the number of days between the start and end date
	public int dateDiff(String date1, String date2) {
		int diff = 0;
		try {
			Date one = getDate(date1);
			Date two = getDate(date2);
			diff = daysBetween(one, two);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		
		return diff;
		
	}
	
	private Date getDate(String date) throws ParseException{
        return df.parse(date);
    }

	public int daysBetween(Date d1, Date d2){
        return Days.daysBetween(
                new LocalDate(d1.getTime()), 
                new LocalDate(d2.getTime())).getDays();
    }


	
	//called from submitTime()
	//MySQL2 returns the total hours of the time off id passed in for this employee
	public int getTimeUsed(int select) {
		//empID, timeOffType
		String s = "Select empID, decision, totalHoursOff, timeOffTypeId FROM v2hrTimeOffRequests WHERE (decision = 'a' OR decision = 'A') AND timeOffTypeId = " + select;
		int totalUsed = this.connection.getTimeUsed(s, this.empID);
		return totalUsed;
	}
	
	//called from startEndTimeOff()
	//checks that the date entered is in the proper format of mm-dd-yyyy
	public String dateCheck() {
		Scanner input = new Scanner(System.in);
		boolean correctForm = false;
		String s = "";
		while(!correctForm) {
			s = input.nextLine();
			if(s.matches("^(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])-[0-9]{4}$")) {
				correctForm = true;
			}else {
				System.out.println();
				System.out.println("The date you entered did not match the required form");
				System.out.print("Please enter the date as follows mm-dd-yyyy: ");
			}
		}
		return s;
	}
	
	//USED FOR BOTH FULL TIME EMPLOYEE AND MANAGERS.  PART TIME HAVE AN OVERRIDDEN TIMEOFFMENU
	public void timeOffMenu() {
		System.out.println();
		System.out.println("TIME OFF MENU");
		System.out.println();
		System.out.println("  1: Vacation Time");
		System.out.println("  2: Floating Holiday Time");
		System.out.println("  3. Sick Time");
		System.out.println("  4: Unpaid Time");
		System.out.println("  5. Bereavement");
		System.out.println();
		System.out.println(" -1: Exit Menu");
	}
	
	//USED FOR BOTH PART TIME AND FULL TIME.  MANAGERS HAVE AN OVERRIDDEN MAINMENU()
	public void mainMenu() {
		System.out.println();
		System.out.println("MAIN MENU");
		System.out.println();
		System.out.println("  1: Request Time Off");
		System.out.println("  2: View Time Off History");
		System.out.println();
		System.out.println(" -1: Exit the application");
		System.out.println();
	}
	
	//USED FOR BOTH PART TIME, FULL TIME, AND MANAGERS
	public void viewHistoryMenu() {
		System.out.println();
		System.out.println("REQUISITION HISTORY BY FILTER MENU");
		System.out.println();
		System.out.println("  1. Filter by: Date Range");
		System.out.println("  2. Filter by: Status");
		System.out.println("  3. Filter by: Time Off Type");
		System.out.println();
		System.out.println(" -1. Exit Menu");
	}
	
	
	
	public void quickTimeOffReport() {
		System.out.println();
		System.out.println("******************************************");
		System.out.println("TIME OFF AT A GLANCE");
		System.out.println();
	}
	
	public int getFloatingHolidayTime() {
		String s = "SELECT FloatingHolidayTime From v2hrEmployee where empID = " + this.empID;
		String t = "FloatingHolidayTime";
		int total = this.connection.getTime(s, t);
		return total;
	}
	
	public int getUsedFloatingHolidayTime() {
		String s = "SELECT totalHoursOff From v2hrTimeOffRequests where empID = " + this.empID + " AND (decision = 'a' OR decision = 'A') AND timeOffTypeId = 2";
		String t = "totalHoursOff";
		int total = this.connection.getTime(s, t);
		return total;
	}
	
	public int getUsedVacationTime() {
		String s = "SELECT totalHoursOff From v2hrTimeOffRequests where empID = " + this.empID + " AND (decision = 'a' OR decision = 'A') AND timeOffTypeId = 1";
		String t = "totalHoursOff";
		int total = this.connection.getTime(s, t);
		return total;
	}
	
	public int getVacationTime() {
		String s = "SELECT VacationTime From v2hrEmployee where empID = " + this.empID;
		String t = "VacationTime";
		int total = this.connection.getTime(s, t);
		return total;
	}
	
	public int getSickTime() {
		String s = "SELECT SickTime From v2hrEmployee where empID = " + this.empID;
		String t = "SickTime";
		int total = this.connection.getTime(s, t);
		return total;
	}
	
	public int getUsedSickTime() {
		String s = "SELECT totalHoursOff From v2hrTimeOffRequests where empID = " + this.empID + " AND (decision = 'a' OR decision = 'A') AND timeOffTypeId = 3";
		String t = "totalHoursOff";
		int total = this.connection.getTime(s, t);
		return total;
	}
	


	
	//CHECK IF THE ENTERED VALUE IS A NUMBER
		public int getNumInput(Scanner input) {
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}
	
	public String getEmpLast() {
		return empLast;
	}
	public void setEmpLast(String empLast) {
		this.empLast = empLast;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpFirst() {
		return empFirst;
	}

	public void setEmpFirst(String empFirst) {
		this.empFirst = empFirst;
	}

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}

	public String getEmpTitle() {
		return empTitle;
	}

	public void setEmpTitle(String empTitle) {
		this.empTitle = empTitle;
	}

	public char getEmpType() {
		return empType;
	}

	public void setEmpType(char empType) {
		this.empType = empType;
	}

	public double getEmpVacationHours() {
		return empVacationHours;
	}

	public void setEmpVacationHours(double empVacationHours) {
		this.empVacationHours = empVacationHours;
	}

	public MySQL2 getConnection() {
		return connection;
	}

	public void setConnection(MySQL2 connection) {
		this.connection = connection;
	}
	
	
}
