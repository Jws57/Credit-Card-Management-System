package Runnable;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Dao_Implementation.JDBC_CustomerDetails_DAO;
import Dao_Implementation.JDBC_TransactionDetails_DAO;

public class Run_TransactionDetails {

	public static void main(String args[]) throws ParseException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		JDBC_TransactionDetails_DAO jdbcTransactionDetailsDAO = new JDBC_TransactionDetails_DAO();
		jdbcTransactionDetailsDAO.getConnection();
		JDBC_CustomerDetails_DAO jdbcCustomerDetailsDAO= new JDBC_CustomerDetails_DAO();
		jdbcCustomerDetailsDAO.getConnection();
		int input=0;
		do {
			
			input = displayQueryMenu();
		if (input == 1) {
			
			jdbcTransactionDetailsDAO.getCustomersTransactionsFromZipcodeInaMonthAndYear();
		} else if (input == 2) {
			jdbcTransactionDetailsDAO.getTotalAndCountOfTransactionType();

		} else if (input == 3) {
			jdbcTransactionDetailsDAO.getNumberAndTotalvaluesOftransactionsInBranchesInaState();
		}  else if (input == 4) {
			jdbcCustomerDetailsDAO.getAccountDetailsOfaCustomer();
		}  else if (input == 5) {
			jdbcCustomerDetailsDAO.modifyAccountDetailsOfaCustomer();
		} else if (input == 6) {
			jdbcCustomerDetailsDAO.getMonthlyBillForCreditCardForMonthAndYear();
		} else if (input == 7) {
			jdbcCustomerDetailsDAO.getCustomerTransactionsBetweenTwoDates ();
		} else {
			System.out.println("\nThat is not a valid option, please try again ");
			
			
		}
		if(input != 0) {
			System.out.println("\nEnter any key to continue or X to Exit");
			String x =sc.nextLine();
			if(x.equals("x") || x.equals("X")) {
				
				break;
			}
		}
		input=0;
		
		}while(input == 0 );
		
		
		
		System.out.println("\n Goodbye :)");
		jdbcTransactionDetailsDAO.closeConnection();
		
		
	}

	static int displayQueryMenu() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int input =0;
		try {
		System.out.println("\nPlease select your Query to run ...");

		System.out.println("\t #1\t Transactions made by customers in given zip code in given month and year");
		System.out.println("\t #2\t Number and total values of transactions for a given type");
		System.out.println("\t #3\t Number and total values of transactions for branches in a given state");
		System.out.println("\t #4\t To check the existing account details of a customer ");
		System.out.println("\t #5\t To modify the existing account details of a customer ");
		System.out.println("\t #6\t To generate a monthly bill for a credit card number for a given month and year");
		System.out.println("\t #7\t To display the transactions made by a customer between two dates. Order by year, month, and day in descending order");
		input = sc.nextInt();
		}catch(InputMismatchException e) {
			
		}
		return input;

	}


}