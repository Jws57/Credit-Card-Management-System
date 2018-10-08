package Dao_Implementation;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import DAO_Interfaces.CustomerDetails_DAO;
import Resources.myQueries;
import ValueObject.TransactionDetails;
import ValueObject.CustomerDetails;

public class JDBC_CustomerDetails_DAO extends myConnection implements CustomerDetails_DAO {

public List<CustomerDetails> getAccountDetailsOfaCustomer() {
		List<CustomerDetails> CustomerDetailss = new LinkedList<CustomerDetails>();
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			PreparedStatement statement = connection.prepareStatement(myQueries.Customer_Details_4);

			System.out.println("\nPlease enter Customer's SSN");
			String input1 = sc.nextLine();
			statement.setString(1, input1);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first() == true) {
				resultSet.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
										// set
				printResultSet(resultSet);

				CustomerDetails CustomerDetails = null;
				while (resultSet.next()) {
					CustomerDetails = new CustomerDetails();
					CustomerDetails.setFirst_name(resultSet.getString("FIRST_NAME"));
					CustomerDetails.setMiddle_name(resultSet.getString("MIDDLE_NAME"));
					CustomerDetails.setLast_name(resultSet.getString("LAST_NAME"));
					CustomerDetails.setSsn(resultSet.getInt("SSN"));
					CustomerDetails.setCredit_card_no(resultSet.getString("CREDIT_CARD_NO"));
					CustomerDetails.setApt_no(resultSet.getString("APT_NO"));
					CustomerDetails.setStreet_name(resultSet.getString("STREET_NAME"));
					CustomerDetails.setCust_city(resultSet.getString("CUST_CITY"));
					CustomerDetails.setCust_state(resultSet.getString("CUST_STATE"));
					CustomerDetails.setCust_country(resultSet.getString("CUST_COUNTRY"));
					CustomerDetails.setCust_zip(resultSet.getString("CUST_ZIP"));
					CustomerDetails.setCust_phone(resultSet.getInt("CUST_PHONE"));
					CustomerDetails.setCust_email(resultSet.getString("CUST_EMAIL"));

					CustomerDetailss.add(CustomerDetails);
				}

				writeResultSet(resultSet);

				resultSet.close();
				statement.close();
			} else {
				System.out.println("No results were found for your query, Please try again");
			}
			// sc.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return CustomerDetailss;

	}

	public int modifyAccountDetailsOfaCustomer() {
		int wasUpdated = 0;
		try {
			String first_name = null, middle_name = null, last_name = null, credit_card_no = null, apt_no = null;
			String street_name = null, cust_city = null, cust_state = null, cust_country = null, cust_zip = null;
			String cust_email = null, ssn = null, cust_phone = null;

			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			PreparedStatement statement = connection.prepareStatement(myQueries.Customer_Details_5);

			System.out.println("\nPlease enter Customer's SSN");
			ssn = sc.nextLine();
			statement.setString(12, ssn);

			System.out.println("\nPlease enter Customer'scredit_card_no");
			credit_card_no = sc.nextLine();
			statement.setString(13, credit_card_no);

			System.out.println(
					"\nPlease select the fields you would like to update (Enter the letter as part of  string)");
			System.out.println("\n A) first_name \t B) middle_name \t C) last_name "
					+ "\n D) apt_no \t E) street_name \t F) cust_city "
					+ "\n G) cust_state \t H) cust_country \t I) cust_zip " + "\n J) cust_phone \t K) cust_email");

			String input = sc.nextLine();

			if (input.indexOf("a") != -1 || input.indexOf("A") != -1) {
				System.out.println("\nPlease enter Customer's first_name");
				first_name = sc.nextLine();

			}
			statement.setString(1, first_name);

			if (input.indexOf("b") != -1 || input.indexOf("B") != -1) {
				System.out.println("\nPlease enter Customer's middle_name");
				middle_name = sc.nextLine();

			}
			statement.setString(2, middle_name);

			if (input.indexOf("c") != -1 || input.indexOf("C") != -1) {
				System.out.println("\nPlease enter Customer's last_name");
				last_name = sc.nextLine();

			}
			statement.setString(3, last_name);

			if (input.indexOf("d") != -1 || input.indexOf("D") != -1) {
				System.out.println("\nPlease enter Customer's apt_no");
				apt_no = sc.nextLine();

			}
			statement.setString(4, apt_no);

			if (input.indexOf("e") != -1 || input.indexOf("E") != -1) {
				System.out.println("\nPlease enter Customer's street_name");
				street_name = sc.nextLine();

			}

			statement.setString(5, street_name);

			if (input.indexOf("f") != -1 || input.indexOf("F") != -1) {
				System.out.println("\nPlease enter Customer's cust_city");
				cust_city = sc.nextLine();

			}
			statement.setString(6, cust_city);

			if (input.indexOf("g") != -1 || input.indexOf("G") != -1) {
				System.out.println("\nPlease enter Customer's cust_state");
				cust_state = sc.nextLine();

			}
			statement.setString(7, cust_state);

			if (input.indexOf("h") != -1 || input.indexOf("H") != -1) {
				System.out.println("\nPlease enter Customer's cust_country");
				cust_country = sc.nextLine();

			}

			statement.setString(8, cust_country);

			if (input.indexOf("i") != -1 || input.indexOf("I") != -1) {
				System.out.println("\nPlease enter Customer's cust_zip");
				cust_zip = sc.nextLine();

			}
			statement.setString(9, cust_zip);
			if (input.indexOf("j") != -1 || input.indexOf("J") != -1) {
				System.out.println("\nPlease enter Customer's cust_phone");
				cust_phone = sc.nextLine();

			}
			statement.setString(10, cust_phone);

			if (input.indexOf("k") != -1 || input.indexOf("K") != -1) {
				System.out.println("\nPlease enter Customer's cust_email");
				cust_email = sc.nextLine();

			}

			statement.setString(11, cust_email);

			wasUpdated = statement.executeUpdate();

			if (input.length() > 0 && wasUpdated > 0) {
				System.out.println("\n Update Completed");
			} else {
				System.out.println("\n No Update was made");
			}
			statement.close();
			// sc.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wasUpdated;

	}

	public List<TransactionDetails> getMonthlyBillForCreditCardForMonthAndYear() {
		// List<CustomerDetails> CustomerDetailss = new LinkedList<CustomerDetails>();
		List<TransactionDetails> TransactionDetailsList = new LinkedList<TransactionDetails>();
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			PreparedStatement statement = connection.prepareStatement(myQueries.Customer_Details_6);

			System.out.println("\nPlease enter Customer's CreditCard number");
			String input1 = sc.nextLine();
			statement.setString(1, input1);

			System.out.println("\nPlease enetr Year");
			String input2 = sc.nextLine();
			statement.setString(2, input2);

			System.out.println("\nPlease Enter Month ");
			String input3 = sc.nextLine();
			statement.setString(3, input3);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.first() == true) {
				resultSet.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
										// set

				printResultSet(resultSet);
				TransactionDetails TransactionDetails = null;

				double TOTAL_TRANSACTION_VALUE = 0;
				while (resultSet.next()) {
					TransactionDetails = new TransactionDetails();
					TransactionDetails.setTransaction_id(resultSet.getString("TRANSACTION_ID"));
					TransactionDetails.setDay(resultSet.getString("DAY"));
					TransactionDetails.setTransaction_type(resultSet.getString("TRANSACTION_TYPE"));
					TransactionDetails.setTransaction_value(resultSet.getString("TRANSACTION_VALUE"));
					TransactionDetailsList.add(TransactionDetails);

					TOTAL_TRANSACTION_VALUE = TOTAL_TRANSACTION_VALUE + resultSet.getDouble("TRANSACTION_VALUE");
				}

				System.out.println("Total valence of all transactions month " + input3 + " of " + input2 + ": $"
						+ TOTAL_TRANSACTION_VALUE);

				writeResultSet(resultSet);
				resultSet.close();
				statement.close();
				// sc.close();

			} else {
				System.out.println("No results were found for your query, Please try again");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return TransactionDetailsList;

	}

	@SuppressWarnings({ "static-access" })
	public List<TransactionDetails> getCustomerTransactionsBetweenTwoDates() throws ParseException {
		// List<CustomerDetails> CustomerDetailss = new LinkedList<CustomerDetails>();
		List<TransactionDetails> TransactionDetailsList = new LinkedList<TransactionDetails>();
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			PreparedStatement statement = connection.prepareStatement(myQueries.Customer_Details_7);

			System.out.println("\nPlease enter Customer's Customer SSN");
			String input1 = sc.nextLine();
			statement.setString(1, input1);

			// System.out.println("");

			System.out.println("\nPlease enter initial Date (mm-dd-yyyy)");
			String input2 = sc.nextLine();
			statement.setString(2, input2);

			System.out.println("\nPlease Enter final Date (mm-dd-yyyy)");
			String input3 = sc.nextLine();
			statement.setString(3, input3);

			// System.out.println(statement);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.first() == true) {
				resultSet.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
				// set
				printResultSet(resultSet);

				TransactionDetails TransactionDetails = null;

				double TOTAL_TRANSACTION_VALUE = 0;
				while (resultSet.next()) {
					TransactionDetails = new TransactionDetails();
					TransactionDetails.setTransaction_id(resultSet.getString("TRANSACTION_ID"));
					TransactionDetails.setMonth(resultSet.getString("MONTH"));
					TransactionDetails.setDay(resultSet.getString("DAY"));
					TransactionDetails.setYear(resultSet.getString("YEAR"));
					TransactionDetails.setTransaction_type(resultSet.getString("TRANSACTION_TYPE"));
					TransactionDetails.setTransaction_value(resultSet.getString("TRANSACTION_VALUE"));
					TransactionDetailsList.add(TransactionDetails);

					TOTAL_TRANSACTION_VALUE = TOTAL_TRANSACTION_VALUE + resultSet.getDouble("TRANSACTION_VALUE");
				}

				System.out.println("Total valence of all transactions from " + input2 + " to " + input3 + ": $"
						+ TOTAL_TRANSACTION_VALUE);

				writeResultSet(resultSet);

				resultSet.close();
				statement.close();
				// sc.close();
			} else {
				System.out.println("No results were found for your query, Please try again");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return TransactionDetailsList;

	}

	static void printResultSet(ResultSet rs) throws SQLException {

		// checks if there are no result in the resultset
		if (rs.first() == false) {
			System.out.println("No results were found for your query, Please try again");
			return;
		}

		rs.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
							// set

		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		ArrayList<String> columns = new ArrayList<String>();

		drawBoxLine(columnCount);
		System.out.print("|");
		for (int i = 1; i <= columnCount; i++) {
			String s = rsmd.getColumnName(i);
			columns.add(s);
			System.out.print(s);

			drawRowSeparation(s.length());
		}
		System.out.println("");

		drawBoxLine(columnCount);

		while (rs.next()) {
			ArrayList<String> row = new ArrayList<String>();
			int columnsSZ = columns.size();
			for (int i = 0; i < columnsSZ; i++) {
				String x = rs.getString(columns.get(i));
				row.add(x);
			}

			if (rs.wasNull()) {
				try {
					throw new NullFieldException();
				} catch (NullFieldException e) {

					System.out.println(e.getMessage());
				}
			}
			int sz = row.size();
			
			System.out.print("|");
			for (int i = 0; i < sz; i++) {
				String s = row.get(i);
				// String dollar = "";
				// try {
				// Double.parseDouble(s);
				// if (s.indexOf(".") != -1) {
				// dollar = "$";
				// }
				// } catch (NumberFormatException nfe) {
				// }
				// System.out.print("|" + dollar + s);

				System.out.print(s);

				drawRowSeparation(s.length());
			}

			System.out.println("");

		}

		drawBoxLine(columnCount);
		rs.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
							// set

		// writeResultSet(rs);

	}

	static void drawBoxLine(int columnCount) {
		System.out.print("+");
		for (int i = 1; i <= columnCount; i++) {

			int Spaces = 24;

			for (int j = 0; j < Spaces; j++) {
				System.out.print("-");
			}

			System.out.print("+");
		}
		System.out.println("");
	}

	static void drawRowSeparation(int sLength) {
		int totalSpace = 20;

		totalSpace = 24 - (sLength);

		for (int j = 0; j < totalSpace; j++) {
			System.out.print(" ");
		}
		System.out.print("|");
	}

	static void writeResultSet(ResultSet rs) throws SQLException {
		// checks if there are no result in the resultset
		if (rs.first() == false) {
			System.out.println("No results written");
			return;
		}
		rs.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
							// set

		DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
		Date date = new Date();

		final String FILENAME = "Results/myResult " + dateFormat.format(date) + ".CSV";

		FileWriter fw = null;
		try {
			fw = new FileWriter(FILENAME);

			// BufferedWriter bw = new BufferedWriter(fw);

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			ArrayList<String> columns = new ArrayList<String>();
			for (int i = 1; i <= columnCount; i++) {
				String s = rsmd.getColumnName(i);
				columns.add(s);
			}

			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				int columnsSZ = columns.size();
				for (int i = 0; i < columnsSZ; i++) {
					String x = rs.getString(columns.get(i));
					row.add(x);
				}

				int sz = row.size();
				for (int i = 0; i < sz; i++) {
					String s = "";
					if (rs.wasNull()) {
						s = " ";
					} else {
						s = row.get(i);
					}
					fw.write(s + ",");

				}

				fw.write("\r\n");

			}

			if (fw != null)
				fw.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

		System.out.println("\nResults Saved to " + FILENAME);
		rs.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
							// set
	}
}
