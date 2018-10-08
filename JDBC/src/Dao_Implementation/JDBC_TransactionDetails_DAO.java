package Dao_Implementation;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import DAO_Interfaces.TransactionDetails_DAO;
import Resources.myQueries;
import ValueObject.TransactionDetails;

public class JDBC_TransactionDetails_DAO extends myConnection implements TransactionDetails_DAO {

	public List<TransactionDetails> getTotalAndCountOfTransactionType() {
		List<TransactionDetails> TransactionDetailsList = new LinkedList<TransactionDetails>();
		try {
			System.out.println("\nPlease enter TRANSACTION_TYPE");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);

			String input1 = sc.nextLine();

			PreparedStatement statement = connection.prepareStatement(myQueries.Customer_Details_2);
			statement.setString(1, input1);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first() == true && !(resultSet.getString(2).equals("0"))) {
				resultSet.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
										// set

				printResultSet(resultSet);

				TransactionDetails TransactionDetails = null;
				while (resultSet.next()) {
					TransactionDetails = new TransactionDetails();
					TransactionDetails.setTransaction_type(resultSet.getString("TRANSACTION_TYPE"));
					TransactionDetails.setCount(resultSet.getString("Number_Of_Transactions"));
					TransactionDetails.setTotalValue(resultSet.getString("Total"));

					TransactionDetailsList.add(TransactionDetails);
				}

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

	public List<TransactionDetails> getCustomersTransactionsFromZipcodeInaMonthAndYear() {
		List<TransactionDetails> TransactionDetailsList = new LinkedList<TransactionDetails>();
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			PreparedStatement statement = connection.prepareStatement(myQueries.Customer_Details_1);

			System.out.println("\nPlease enter YEAR");
			String input1 = sc.nextLine();
			statement.setString(1, input1);

			System.out.println("\nPlease enter MONTH");
			String input2 = sc.nextLine();
			statement.setString(2, input2);

			System.out.println("\nPlease enter ZIP_CODE");
			String input3 = sc.nextLine();
			statement.setString(3, input3);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first() == true) {
				resultSet.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
										// set

				printResultSet(resultSet);
				TransactionDetails TransactionDetails = null;
				while (resultSet.next()) {
					TransactionDetails = new TransactionDetails();
					TransactionDetails.setFirst_name(resultSet.getString("FIRST_NAME"));
					TransactionDetails.setLast_name(resultSet.getString("LAST_NAME"));
					TransactionDetails.setTransaction_value(resultSet.getString("TRANSACTION_VALUE"));
					TransactionDetailsList.add(TransactionDetails);
				}

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

	public List<TransactionDetails> getNumberAndTotalvaluesOftransactionsInBranchesInaState() {
		List<TransactionDetails> TransactionDetailsList = new LinkedList<TransactionDetails>();
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			PreparedStatement statement = connection.prepareStatement(myQueries.Customer_Details_3);

			System.out.println("\nPlease enter STATE");
			String input1 = sc.nextLine();
			statement.setString(1, input1);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first() == true && !(resultSet.getString(1).equals("0"))) {
				resultSet.beforeFirst();// resets the resultSet, moves the cursor back to the beginning of the result
										// set
				printResultSet(resultSet);
				TransactionDetails TransactionDetails = null;
				while (resultSet.next()) {
					TransactionDetails = new TransactionDetails();
					TransactionDetails.setCount(resultSet.getString("Number_Of_Transactions"));
					TransactionDetails.setTotalValue(resultSet.getString("Total_value"));
					TransactionDetailsList.add(TransactionDetails);
				}

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
