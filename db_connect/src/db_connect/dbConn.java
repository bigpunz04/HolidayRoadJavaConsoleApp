package db_connect;

import java.sql.*;
import java.util.Scanner;

public class dbConn {
public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);
	
		System.out.println("*************************");
		System.out.println("Welcome to Holiday Road");
		System.out.println("Your Time Off Starts Here");
		System.out.println("*************************");
		System.out.println();
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("Enter your Employee ID or enter a '-1' to view the directory");
		System.out.print("Selection: ");
		int input_id = input.nextInt();
		System.out.println("************************************************************");
		
		
		/*
		try {
			Connection conn = null;
            String connectStr = "jdbc:mysql://cs.neiu.edu:3306/18f315_Rhein?" + "user=18f315_Rhein&password=Reesey1015!";
            conn = DriverManager.getConnection(connectStr);
            Statement stmt = conn.createStatement();
            
            
            ResultSet listing = stmt.executeQuery("SELECT * FROM hrTimeOffRequests");
			while(listing.next()) {
				System.out.println("did i get here?");
				int id = listing.getInt("empID");
				System.out.println(id);
			}
			
		
		}
		catch(Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
            //System.out.println("SQLState: " + ex.getSQLState());
            //System.out.println("VendorError: " + ex.getErrorCode());
		}
		*/
	}
}
