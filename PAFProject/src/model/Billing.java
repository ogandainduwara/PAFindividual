package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billing {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/egsys", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertbilling(String AccNo, String BillUnit) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting!.";
			}
			// create a prepared statement
			String query = " insert into bill_calculation(`bID`,`AccNo`,`BillUnit`)" + " values (?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, AccNo);
			preparedStmt.setString(3, BillUnit);

			// execute the statement

			preparedStmt.execute();
			con.close();
			String newBilling = readbilling();
			output = "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readbilling() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Account Number</th><th>Bill Units</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from bill_calculation";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String bID = Integer.toString(rs.getInt("bID"));
				String AccNo = rs.getString("AccNo");
				String BillUnit = rs.getString("BillUnit");

				// Add into the html table

				output += "<td>" + AccNo + "</td>";
				output += "<td>" + BillUnit + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + bID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + bID + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatebilling(String bID, String AccNo, String BillUnit)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE bill_calculation SET AccNo=?,BillUnit=?WHERE bID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

			preparedStmt.setString(1, AccNo);
			preparedStmt.setString(2, BillUnit);
			preparedStmt.setInt(3, Integer.parseInt(bID));

// execute the statement
			preparedStmt.execute();
			con.close();
			String newBilling = readbilling();
			output = "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletebilling(String bID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from bill_calculation where bID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(bID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newBilling = readbilling();
			output = "{\"status\":\"success\", \"data\": \"" + newBilling + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
