
import java.sql.*;
public class empLL {
	private empNode m_first;
	private int empCount = 0;
	private int columns = 7;
	
	public empLL() {
		m_first = null;
	}
	
	//INSERT AN EMPLOYEE INTO A NODE.  ENSURE THAT THE LIST IS SORTED
	public void insertEmployee(int id, String password, String firstName, String lastName, String department, String title, char type, double hours) {
		
		empCount++;
		
		empNode newEmployee = new empNode(id, password, firstName, lastName, department, title, type, hours); 
		
		empNode current = m_first;
		empNode previous = null;
		
		while(current != null) {
			if(current.getEmpID() < id) {
				previous = current;
				current = current.getLink();
			}else break;
		}
		
		if(previous == null) {
			newEmployee.setLink(current);
			m_first = newEmployee;
		}else {
			newEmployee.setLink(current);
			previous.setLink(newEmployee);
		}
		
	}
	
	public int getEmpCount() {
		return empCount;
	}

	public void setEmpCount(int empCount) {
		this.empCount = empCount;
	}

	public void displayList() {
		empNode current = m_first;
		System.out.printf("%-20s %-25s %-25s %-20s %-20s\n", "EMPLOYEE ID", "EMPLOYEE NAME", "EMPLOYEE DEPARTMENT", "TITLE", "STATUS");
		System.out.println("--------------------------------------------------------------------------------------------------------");
		while(current != null) {
			
			String status = "";
            
            if(current.getEmpType() == 'f' || current.getEmpType() == 'F') {
            	status += "Full Time";
            }else status += "Part Time";
            
            System.out.printf("%-20s %-25s %-25s %-20s %-20s\n", current.getEmpID(), current.getEmpFirst() + " " + current.getEmpLast(), current.getEmpDepartment(), current.getEmpTitle(), status);
            System.out.println();
			
			System.out.println();
			current = current.getLink();
		}
	}
	
	public boolean contains(int id) {
		empNode current = m_first;
		while(current != null) {
			if(current.getEmpID() == id) {
				return true;
			}else current = current.getLink();
		}
		
		return false;
	}
	
	public empNode getEmpInfo(int id) {
		empNode current = m_first;
		while(current != null) {
			if(current.getEmpID() == id) {
				break;
			}else current = current.getLink();
		}
		return current;
	}
}
