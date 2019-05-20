import java.util.Scanner;

public class FullTimeManager extends Employee implements empInterface{
	
	private empLL empList;
	
	public FullTimeManager(empNode emp, MySQL2 sqlConn, empLL list) {
		super(emp, sqlConn);
		this.empList = list;
	}
	
	@Override
	public boolean mainMenuSelect(int select) {
		Scanner input = new Scanner(System.in);
		
			if(select == -1) {
				return false;
			}else if(select == 1) {
				quickTimeOffReport();
				boolean cont = true;
				while(cont) {
					timeOffMenu();
					int timeOffSelect = getNumInput(input);
					if(timeOffSelect == -1) {
						cont = false;
					}else submitTime(timeOffSelect);
				}
				return true;
			}else if(select == 2) {
				boolean cont = true;
				while(cont) {
					viewHistoryMenu();
					int filterSelect = getNumInput(input);
					if(filterSelect == -1) {
						cont = false;
					}else viewHistory(filterSelect);
				}
				return true;
			}else if(select == 3) {
				boolean cont = true;
				while(cont) {
					viewHistoryMenuManager();
					int filterSelect = getNumInput(input);
					if(filterSelect == -1) {
						cont = false;
					}else createFilter(filterSelect);
				}
				return true;
			}else if(select == 4) {
				boolean cont = true;
				while(cont) {
					cont = addEmployee(input);
				}
				return true;
			}
		return false;
	}
	
	public boolean addEmployee(Scanner input) {
		System.out.println("ADD AN EMPLOYEE TO THE DATABASE");
		System.out.println();
		System.out.println("Enter the following pieces of information");
		System.out.println();
		System.out.print("First Name: ");
		String first = input.nextLine();
		System.out.print("Last Name: ");
		String last = input.nextLine();
		System.out.print("Department: ");
		String dept = input.nextLine();
		System.out.print("Title: ");
		String title = input.nextLine();
		System.out.print("Employment Status (F- Full Time or P - Part Time): ");
		
		String status = input.next();
		System.out.print("4 Digit ID: ");
		int id = input.nextInt();
		System.out.print("Vacation Hours: ");
		int vacHours = input.nextInt();
		System.out.print("Sick Hours: ");
		int sickHours = input.nextInt();
		System.out.print("Floating Holiday Hours: ");
		int fHolidayHours = input.nextInt();
		
		createEmployee(first, last, dept, title, status, id, vacHours, sickHours, fHolidayHours);
		System.out.println("Employee entered!");
		System.out.println();
		
		return false;
	}
	
	public void createEmployee(String first, String last, String dept, String title, String status, int id, int vacHours, int sickHours, int fHolidayHours) {
		String s = "Insert into v2hrEmployee VALUES (" + id + ", 'DEFAULT' , '" + first + "', '" + last + "', '" + dept + "', '" + title + "', '" + status + "', " + 80.0 + ", " + vacHours + ", " + sickHours + ", " + fHolidayHours + ")";
		super.getConnection().addRequest(s);
	}
	
	public void createFilter(int select) {
		Scanner input = new Scanner(System.in);
		if(select ==1) {
			System.out.println("You have selected to view history based on a date range");
			System.out.println();
			String startDate = getStartDate("date range");
			System.out.println();
			String endDate = getEndDate("date range");
			timeOffLL dateList = createTOLLDateRange(startDate, endDate);
			System.out.println();
			System.out.println("ALL REQUISITION MADE BETWEEN " + startDate + " AND " + endDate);
			System.out.println();
			dateList.displayList();
			reviewReq(input, dateList);
		}else if(select == 2) {
			System.out.println("You have chosen to view history based on status");
			System.out.println();
			System.out.println("STATUS MENU");
			System.out.println();
			System.out.println("  1. Approved Requisitions");
			System.out.println("  2. Declined Requisitions");
			System.out.println("  3. Pending Requisitions");
			System.out.println();
			System.out.println(" -1. Exit Menu");
			System.out.println();
			int status = getNumInput(input);
			if(status == -1) {
				//exit
			}else if(status == 1  || status == 2 || status == 3) {
				timeOffLL dateList = createTOLLStatus(status);
				dateList.displayList();
				reviewReq(input, dateList);
			}else System.out.println("Invalid entry.  Please try again");
		}else if(select == 3) {
			System.out.println("You have chosen to view history based on time off type");
			System.out.println();
			System.out.println("TIME OFF TYPE MENU");
			System.out.println();
			System.out.println("  1. Vacation Requisitions");
			System.out.println("  2. Floating Holiday Requisitions");
			System.out.println("  3. Sick Requisitions");
			System.out.println("  4. Unpaid Requisitions");
			System.out.println("  5. Bereavement Requisitions");
			System.out.println();
			System.out.println(" -1. Exit Menu");
			System.out.println();
			int type = getNumInput(input);
			if(type == -1) {
				
			}else if(type >= 1 || type <= 5) {
				timeOffLL statusList = createTOLLType(type);
				statusList.displayList();
				reviewReq(input, statusList);
			}else System.out.println("Invalid entry.  Please try again.");
			
		}else if(select == 4) {
			System.out.println("You have chosen to view history based on employee ID");
			System.out.println();
			System.out.println("Enter the employee ID");
			System.out.println();
			int id = getNumInput(input);
			if(this.empList.contains(id)) {
				timeOffLL empIDList = createTOLLEmpID(id);
				empIDList.displayList();
				reviewReq(input, empIDList);
			}else System.out.println("Invalid entry.  ID not found.  Please try again");
			
		}
	}
	
	public timeOffLL createTOLLEmpID(int id) {
		String s = "SELECT * FROM timeOffRequestsView WHERE empID = " + id + " ORDER BY dateRequested;";
		timeOffLL newList = super.getConnection().getRequisitionsByDate(s);
		return newList;
	}
	
	public timeOffLL createTOLLType(int type) {
		String s = "SELECT * FROM timeOffRequestsView WHERE timeOffTypeId = " + type + " ORDER BY dateRequested;";
		timeOffLL newList = super.getConnection().getRequisitionsByDate(s);
		return newList;
	}
	
	public void reviewReq(Scanner input, timeOffLL dateList) {

		boolean cont = true;
		while(cont) {
			System.out.println();
			System.out.print("Enter the Requisition ID: ");
			int reqID = getNumInput(input);
			System.out.println();
			if(dateList.contains(reqID)) {
				reviewMenu();
				int decisionSelect = getNumInput(input);
				if(decisionSelect != 1 && decisionSelect != 2) {
					System.out.println("Invalid entry.  Please try again.");
				}else{
					System.out.println();
					System.out.println("Enter a short note about your determination: ");
					input.nextLine();
					String s = input.nextLine();
					updateDateRangeRequisition(reqID, decisionSelect, s);
					System.out.println("Requisition ID# " + reqID + " has been updated.");
				}
			}else if(reqID == -1) {
				cont = false;
			}else System.out.println("Requisition ID not found.  Please try again.");
		}
	}
	
	
	public void reviewMenu() {
		System.out.println("Enter your determination");
		System.out.println();
		System.out.println("  1. Approve");
		System.out.println("  2. Decline");
		System.out.println();
		System.out.println(" -1. Exit Menu");
	}
	
	public timeOffLL createTOLLStatus(int status) {
		String s = "";
		if(status == 1) {
			s = "SELECT * FROM timeOffRequestsView WHERE (decision = 'a' OR decision = 'A') ORDER BY dateRequested;";
		}else if(status == 2) {
			s = "SELECT * FROM timeOffRequestsView WHERE (decision = 'd' OR decision = 'D') ORDER BY dateRequested;";
		}else if(status == 3) {
			s = "SELECT * FROM timeOffRequestsView WHERE (decision = 'p' OR decision = 'P') ORDER BY dateRequested;";
		}
		
		timeOffLL newList = super.getConnection().getRequisitionsByDate(s);
		return newList;
	}
	
	public void updateDateRangeRequisition(int reqID, int decision, String note) {
		char dec;
		if(decision == 1) {
			dec = 'A';
		}else dec = 'D';
		
		String s = "UPDATE v2hrTimeOffRequests SET decision = '" + dec + "', managerNote = '" + note + "' WHERE reqID = " + reqID;
		super.getConnection().addRequest(s);
	}
	
	public timeOffLL createTOLLDateRange(String date1, String date2) {
		String s = "SELECT * FROM timeOffRequestsView WHERE (dateRequested >= '" + date1 + "' AND dateRequested <= '" + date2 + "') ORDER BY dateRequested;";
		timeOffLL newList = super.getConnection().getRequisitionsByDate(s);
		return newList;
	}
	
	public char getCharInput(Scanner input) {
		char c = 'z';
		while(!(Character.toString(c).matches("[adAD]"))) {
			c = input.next(".").charAt(0);
			if(!(Character.toString(c).matches("[adAD]"))) {
				System.out.println("Invalid entry.  Please try again");
			}
		}
		return c;
	}
	
	
	
	public void viewHistoryMenuManager() {
		System.out.println();
		System.out.println("APPROVE/DECLINE TIME OFF REQUESTS");
		System.out.println();
		System.out.println("REQUISITION HISTORY BY FILTER MENU");
		System.out.println();
		System.out.println("  1. Filter by: Date Range");
		System.out.println("  2. Filter by: Status");
		System.out.println("  3. Filter by: Time Off Type");
		System.out.println("  4. Filter by: Employee ID");
		System.out.println();
		System.out.println(" -1. Exit Menu");
	}
	
	@Override
	public void mainMenu() {
		System.out.println();
		System.out.println("MAIN MENU");
		System.out.println();
		System.out.println("  1: Request Time Off");
		System.out.println("  2: View Time Off History");
		System.out.println("  3. Approve/Decline Time Off Requests");
		System.out.println("  4. Add New Employee");
		System.out.println();
		System.out.println(" -1: Exit the application");
		System.out.println();
	}
	
	@Override
	public void quickTimeOffReport() {
		super.quickTimeOffReport();
		System.out.println("Total Vacation Hours: " + super.getVacationTime());
		System.out.println("Total Vacation Hours Used: " + super.getUsedVacationTime());
		System.out.println();
		System.out.println("Total Floating Holiday Hours: " + super.getFloatingHolidayTime());
		System.out.println("Total Floating Holiday Hours Used: " + super.getUsedFloatingHolidayTime());
		System.out.println();
		System.out.println("Total Sick Hours: " + super.getSickTime());
		System.out.println("Total Sick Hours Used: " + super.getUsedSickTime());
		System.out.println();
		System.out.println("******************************************");

	}

	@Override
	public void submitTime(int select) {
		super.submitTime(select);
		
	}

	@Override
	public void viewTime(String stmt) {
		// TODO Auto-generated method stub
		
	}
}
