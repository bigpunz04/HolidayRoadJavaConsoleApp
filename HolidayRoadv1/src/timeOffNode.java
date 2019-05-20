
public class timeOffNode {
	private int reqID;
	private String dateRequested;
	private int empID;
	private int timeOffTypeID;
	private char decision;
	private String startDate;
	private String endDate;
	private int totalHoursOff;
	private String empNote;
	private String managerNote;
	private String first;
	private String last;
	
	private timeOffNode link;

	public timeOffNode(int reqID, String dateRequested, int empID, int timeOffTypeID, char decision, String startDate, String endDate, int totalHoursOff, String empNote, String managerNote, String first, String last) {
		this.reqID = reqID;
		this.dateRequested = dateRequested;
		this.empID = empID;
		this.timeOffTypeID = timeOffTypeID;
		this.decision = decision;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalHoursOff = totalHoursOff;
		this.empNote = empNote;
		this.managerNote = managerNote;
		this.first = first;
		this.last = last;
		
		this.link = null;
	}

	
	//Setters and Getters
	
	public String getFirst() {
		return first;
	}


	public void setFirst(String first) {
		this.first = first;
	}


	public String getLast() {
		return last;
	}


	public void setLast(String last) {
		this.last = last;
	}


	public int getReqID() {
		return reqID;
	}

	public void setReqID(int reqID) {
		this.reqID = reqID;
	}

	public String getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(String dateRequested) {
		this.dateRequested = dateRequested;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public int getTimeOffTypeID() {
		return timeOffTypeID;
	}

	public void setTimeOffTypeID(int timeOffTypeID) {
		this.timeOffTypeID = timeOffTypeID;
	}

	public char getDecision() {
		return decision;
	}

	public void setDecision(char decision) {
		this.decision = decision;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getTotalHoursOff() {
		return totalHoursOff;
	}

	public void setTotalHoursOff(int totalHoursOff) {
		this.totalHoursOff = totalHoursOff;
	}

	public String getEmpNote() {
		return empNote;
	}

	public void setEmpNote(String empNote) {
		this.empNote = empNote;
	}

	public String getManagerNote() {
		return managerNote;
	}

	public void setManagerNote(String managerNote) {
		this.managerNote = managerNote;
	}

	public timeOffNode getLink() {
		return link;
	}

	public void setLink(timeOffNode link) {
		this.link = link;
	}
	

	
	
	
}
