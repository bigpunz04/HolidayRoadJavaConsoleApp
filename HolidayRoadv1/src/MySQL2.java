import java.sql.*;
import java.util.TimeZone;

public class MySQL2 {
	private String sqlStatement;
	
	//CONNECTION CONSTRUCTOR
	public MySQL2() {

		//login credentials for phpMyAdmin
		this.sqlStatement = "jdbc:mysql://cs.neiu.edu:3306/18f315_Rhein?user=me&pass=secret&useLegacyDatetimeCode=false&serverTimezone=Europe/Vienna" + "user=18f315_Rhein&password=Reesey1015!";
		
		//FOR ALL SELECT STATEMENTS
		/*
		try {
			Connection conn = null;
            conn = DriverManager.getConnection(this.sqlStatement);
            Statement stmt = conn.createStatement();
            ResultSet listing = stmt.executeQuery("SELECT * FROM v2hrEmployee");
                
            }
		}
		catch(SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
         }
         */
		
		//FOR ALL UPDATE STATEMENTS
		/*
		
		try{
			Connection conn = null;
			conn = DriverManager.getConnection(this.sqlStatement);
			this.stmt = conn.createStatement();
			stmt.executeUpdate(insertStmt);
		}
		 catch(SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		*/
	}

	public empLL getDirectory(String sqlStmt) {
		empLL empList = new empLL();
		
		try {
			Connection conn = null;
            conn = DriverManager.getConnection(this.sqlStatement);
            Statement stmt = conn.createStatement();
            ResultSet listing = stmt.executeQuery("SELECT * FROM v2hrEmployee");
                
            while(listing.next()) {
            	
            	int empID = listing.getInt("empID");
                String empPassword = listing.getString("empPassword");
                String empFirst = listing.getString("empFirst");
                String empLast = listing.getString("empLast");
                String empDepartment = listing.getString("empDept");
                String empTitle = listing.getString("empTitle");
                char empType = listing.getString("empType").charAt(0);
                double empVacationHours = listing.getDouble("empVacationHours");
                
                //CREATE A NODE.
                empList.insertEmployee(empID, empPassword, empFirst, empLast, empDepartment, empTitle, empType, empVacationHours);
                System.out.println(1);
                
            }
		}
		catch(SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
         }
		
		return empList;
	}
	
	public int getTimeUsed(String sqlStmt, int id) {
		int used = 0;
		try {
			Connection conn = null;
            conn = DriverManager.getConnection(this.sqlStatement);
            Statement stmt = conn.createStatement();
            ResultSet listing = stmt.executeQuery(sqlStmt);
                
            while(listing.next()) {
            	int listID = listing.getInt("empID");
            	if(listID == id) {
            		used += listing.getInt("totalHoursOff");
            	}
            	
            }
		}
		catch(SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
         }
		return used;
		
	}
	
	public void addRequest(String sqlStmt) {
		try{
			Connection conn = null;
			conn = DriverManager.getConnection(this.sqlStatement);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlStmt);
		}
		 catch(SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public int getTime(String sqlStmt, String type) {
		int result = 0;
		try {
			Connection conn = null;
            conn = DriverManager.getConnection(this.sqlStatement);
            Statement stmt = conn.createStatement();
            ResultSet listing = stmt.executeQuery(sqlStmt);
                
            while(listing.next()) {
            	result += listing.getInt(type);
            }
		}
		catch(SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
         }
		return result;
	}

	public timeOffLL getRequisitionsByDate(String sqlStmt) {
		timeOffLL byDateList = new timeOffLL();
		try {
			Connection conn = null;
            conn = DriverManager.getConnection(this.sqlStatement);
            Statement stmt = conn.createStatement();
            ResultSet listing = stmt.executeQuery(sqlStmt);
                
            while(listing.next()) {
            	int reqID = listing.getInt("reqID");
            	String dateRequested = listing.getString("dateRequested");
            	int empID = listing.getInt("empID");
            	int timeOffTypeID = listing.getInt("timeOffTypeId");
            	char decision = listing.getString("decision").charAt(0);
            	String startDate = listing.getString("startDate");
            	String endDate = listing.getString("endDate");
            	int totalHoursOff = listing.getInt("totalHoursOff");
            	String empNote = listing.getString("empNote");
            	String managerNote = listing.getString("managerNote");
            	String first = listing.getString("empFirst");
            	String last = listing.getString("empLast");
            	
            	//insert into list
            	byDateList.insertTimeOffRequest(reqID, dateRequested, empID, timeOffTypeID, decision, startDate, endDate, totalHoursOff, empNote, managerNote, first, last);
            }
		}
		catch(SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
         }
		return byDateList;
	}
}
