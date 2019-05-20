
public class empNode {
	private int empID;
	private String empPassword;
	private String empFirst;
	private String empLast;
	private String empDepartment;
	private String empTitle;
	private char empType;
	private double empVacationHours;
	   
	private empNode link;
	
	public empNode(int id, String password, String firstName, String lastName, String department, String title, char type, double hours){
		   
	      this.empID = id;
	      this.empPassword = password;
	      this.empFirst = firstName;
	      this.empLast = lastName;
	      this.empDepartment = department;
	      this.empTitle = title;
	      this.empType = type;
	      this.empVacationHours = hours;
	      
	      this.link = null;
	      
	   }

	public double getEmpVacationHours() {
		return empVacationHours;
	}

	public void setEmpVacationHours(double empVacationHours) {
		this.empVacationHours = empVacationHours;
	}

	public String getEmpFirst() {
		return empFirst;
	}

	public void setEmpFirst(String empFirst) {
		this.empFirst = empFirst;
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

	public String getEmpLast() {
		return empLast;
	}

	public void setEmpLast(String empLast) {
		this.empLast = empLast;
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

	public empNode getLink() {
		return link;
	}

	public void setLink(empNode link) {
		this.link = link;
	}
}
