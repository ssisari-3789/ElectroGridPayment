package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, u$ername, password..
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/payment_records?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String customerName,String accountNum,String Paydate,String Pemail,String totAmount ) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment(`PaymentID`,`customerName`, `accountNum`, `Paydate`,`Pemail`,`totAmount`)" + " values (?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerName);
			preparedStmt.setString(3, accountNum);
			preparedStmt.setString(4, Paydate);
			preparedStmt.setString(5, Pemail);
			preparedStmt.setString(6, totAmount);
	

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Payment Inserted successfully!";
		} catch (Exception e) {
			output = "Error while inserting  Payment Details!";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading!";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr> <th>Payment ID</th><th>Customer Name</th><th>Account Name</th><th>Date</th><th>Email</th><th>Total Amount</th> </tr>";
			String query = "select * from payment";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String PaymentID = Integer.toString(rs.getInt("PaymentID"));
				String customerName = rs.getString("customerName");
				String accountNum = rs.getString("accountNum");
				String Paydate = rs.getString("Paydate");
				String Pemail = rs.getString("Pemail");
				String totAmount = rs.getString("totAmount");


				output += "<tr><td>" + PaymentID + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + accountNum + "</td>";
				output += "<td>" + Paydate + "</td>";
				output += "<td>" + Pemail + "</td>";
				output += "<td>" + totAmount + "</td>";
			

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment Details!";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updatePayment(String PaymentID, String customerName,String accountNum,String Paydate,String Pemail,String totAmount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating!";
			}

			// create a prepared statement
			String query = "UPDATE payment SET customerName=?,accountNum=?,Paydate=?,Pemail=?,totAmount=? WHERE PaymentID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			

			preparedStmt.setString(1, customerName);
			preparedStmt.setString(2, accountNum);
			preparedStmt.setString(3, Paydate);
			preparedStmt.setString(4, Pemail);
			preparedStmt.setString(5, totAmount);
			preparedStmt.setInt(6, Integer.parseInt(PaymentID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Payment Updated successfully!";
		} catch (Exception e) {
			output = "Error while updating!";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	public String deletePayment(String PaymentID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting!";
			}

			// create a prepared statement
			String query = "delete from payment where PaymentID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PaymentID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully!";
		} catch (Exception e) {
			output = "Error while deleting!";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
