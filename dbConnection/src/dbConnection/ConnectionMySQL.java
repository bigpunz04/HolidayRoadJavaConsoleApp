package dbConnection;

import java.sql.DriverManager;
import java.sql.*;

public class ConnectionMySQL {
	public static void main(String[] args) {
		
		try {
			Connection conn = null;
			String connect = "jdbc:mysql://cs.neiu.edu:3306/18f315_Rhein?" + "user=18f315_Rhein&password=Reesey1015!";
			conn = DriverManager.getConnection(connect);
			
			System.out.println("MySQL - Connected");
			
			Statement stmt = conn.createStatement();
			
			String selectStatement = "SELECT * FROM hrEmployees";
			
			ResultSet listing = stmt.executeQuery(selectStatement);
			while(listing.next()) {
				System.out.println("did i get here?");
				int id = listing.getInt("empID");
				System.out.println(id);
			}
			
		
		}
		catch(Exception e) {
			
		}
	}
}

