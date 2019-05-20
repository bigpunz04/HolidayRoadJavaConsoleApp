
public class FullTimeEmployee extends Employee implements empInterface {
	
	public FullTimeEmployee(empNode emp, MySQL2 sqlConn) {
		super(emp, sqlConn);
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
