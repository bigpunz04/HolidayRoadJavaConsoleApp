import java.util.Scanner;
public class PartTimeEmployee extends Employee implements empInterface {
	
	public PartTimeEmployee(empNode emp, MySQL2 sqlConn) {
		super(emp, sqlConn);
	}
	
	@Override
	public void viewHistory(int select) {
		Scanner input = new Scanner(System.in);
		if(select == 1) {
			System.out.println("You have selected to view history based on a date range");
			System.out.println();
			String startDate = super.getStartDate("history");
			System.out.println();
			String endDate = super.getEndDate("history");
			
			timeOffLL dateList = super.createSelectRangeSQLStmt(startDate, endDate);
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
			int statusSelection = super.getNumInput(input);
			String type ="";
			timeOffLL statusList = super.createSelectStatusSQLStmt(statusSelection);
			statusList.displayList();
		}else if(select == 3) {
			System.out.println();
			System.out.println("You have selected to view history based on type");
			System.out.println();
			System.out.println("TYPE MENU");
			System.out.println();
			System.out.println("  1: Floating Holiday Requests");
			System.out.println("  2: Sick Requests");
			System.out.println("  3: Unpaid Requests");
			System.out.println("  4: Bereavement Requests");
			System.out.println();
			System.out.println(" -1: Exit Menu");
			System.out.println();
			int typeSelect = getNumInput(input);
			String type ="";
			timeOffLL typeList = super.createSelectTypeSQLStmt(typeSelect+1);
			typeList.displayList();
		}
	}

	@Override
	public void timeOffMenu() {
		System.out.println();
		System.out.println("  1. Floating Holiday");
		System.out.println("  2: Sick");
		System.out.println("  3. Unpaid");
		System.out.println("  4. Bereavement");
		System.out.println();
		System.out.println(" -1: Exit Menu");
	}
	
	@Override
	public void quickTimeOffReport() {
		super.quickTimeOffReport();
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
		Scanner input = new Scanner(System.in);
		
		//PART TIME FLOATING HOLIDAY
		if(select == 1) {
			System.out.println("You have selected to submit a Floating Holiday Request");
			System.out.println();
			String start = super.getStartDate("Floatng Holiday");
			System.out.println();
			String end = super.getEndDate("Floating Holiday");
			System.out.println();
			int startEndInHours = super.dateDiff(start, end) * 8;
			int timeUsed = super.getTimeUsed(2);
			if(super.getFloatingHolidayTime() - timeUsed > startEndInHours) {
				System.out.println("If this floating holiday request is approved, you will be left with " + (super.getFloatingHolidayTime() - timeUsed - startEndInHours) + " hours");
				String note = super.getNote(input);
				super.createInsertSQLStmt(super.getToday(), 2, start, end, startEndInHours, note); //2 = floating holiday in db
				super.submittedMessage();

			}else System.out.println("You do not have enough floating holiday time for the date range you entered");
		
		//PART TIME SICK TIME
		}else if(select == 2) {
			System.out.println("You have selected to submit a Sick Request");
			System.out.println();
			String endDate = super.getEndDate("sick");
			System.out.println();
			int startEndInHours = super.dateDiff(super.getToday(), endDate) * 8;
			int timeUsed = super.getTimeUsed(2);
			if(super.getSickTime() - timeUsed > startEndInHours) {
				System.out.println("If this sick time request is approved, you will be left with " + (super.getSickTime() - timeUsed - startEndInHours) + " hours.");
				String note = super.getNote(input);
				super.createInsertSQLStmt(super.getToday(), 3, super.getToday(), endDate, startEndInHours, note);
				super.submittedMessage();
			}
			
		//PART TIME UNPAID
		}else if(select == 3) {
			System.out.println("You have selected to submit an Unpaid Request");
			System.out.println();
			String startDate = super.getStartDate("unpaid");
			System.out.println();
			String endDate = super.getEndDate("unpaid");
			System.out.println();
			int startEndInHours = super.dateDiff(super.getToday(), endDate) * 8;
			int timeUsed = getTimeUsed(select);
			System.out.println("You are submitting an unpaid time regquest for a total of " + startEndInHours + " hours.");
			String note = getNote(input);
			super.createInsertSQLStmt(super.getToday(), 4, startDate, endDate, startEndInHours, note);
			super.submittedMessage();
			
		//PART TIME BEREAVEMENT
		}else if(select == 4) {
			System.out.println("You have selected to submit a Bereavement Request");
			System.out.println();
			String startDate = getStartDate("bereavement");
			System.out.println();
			String endDate = getEndDate("bereavement");
			System.out.println();
			int startEndInHours = dateDiff(startDate, endDate) * 8;
			int timeUsed = getTimeUsed(select);
			System.out.println("You are submitting a bereavement time request for a total of " + startEndInHours + " hours.");
			String note = super.getNote(input);
			super.createInsertSQLStmt(super.getToday(), 5, startDate, endDate, startEndInHours, note);
			submittedMessage();
		}else System.out.println("You have made an invalid choice");		
	}

	@Override
	public void viewTime(String stmt) {
		// TODO Auto-generated method stub
		
	}
}
