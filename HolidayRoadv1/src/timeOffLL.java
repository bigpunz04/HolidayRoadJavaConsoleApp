
public class timeOffLL {
	private timeOffNode m_first;
	private int count;
	
	public timeOffLL() {
		m_first = null;
		this.count = 0;
	}
	
	public void insertTimeOffRequest(int reqID, String dateRequested, int empID, int timeOffTypeID, char decision, String startDate, String endDate, int totalHoursOff, String empNote, String managerNote, String first, String last) {
		timeOffNode newNode = new timeOffNode(reqID, dateRequested, empID, timeOffTypeID, decision, startDate, endDate, totalHoursOff, empNote, managerNote, first, last);
		this.count++;
		if(m_first == null) {
			m_first = newNode;
		}else {
			newNode.setLink(m_first);
			m_first = newNode;
		}
	}
	
	public boolean contains(int reqID) {
		timeOffNode current = m_first;
		while(current != null) {
			if(current.getReqID() == reqID) {
				return true;
			}else current = current.getLink();
		}
		
		return false;
	}
	
	public void displayList() {
		timeOffNode current = m_first;
		System.out.println();
		System.out.printf("%-20s %-20s %-25s %-25s %-25s %-20s %-20s\n", "REQUISITION ID", "DATE REQUESTED", "EMPLOYEE NAME", "TIME OFF TYPE", "HOURS REQUESTED", "STATUS", "MANAGER NOTE");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(current != null) {
			String type = "";
			if(current.getTimeOffTypeID() == 1) {
				type = "Vacation";
			}else if(current.getTimeOffTypeID() == 2) {
				type = "Floating Holiday";
			}else if(current.getTimeOffTypeID() == 3) {
				type = "Sick Time";
			}else if(current.getTimeOffTypeID() == 4) {
				type = "Unpaid Request";
			}else type = "Bereavement";
			
			System.out.printf("%-20s %-20s %-25s %-25s %-25s %-20s %-20s\n", current.getReqID(), current.getDateRequested(), current.getFirst() + " " + current.getLast(), type, current.getTotalHoursOff(), current.getDecision(), current.getManagerNote());
			current = current.getLink();
		}
	}
	
	public int getCount() {
		return count;
	}
	
	
}
