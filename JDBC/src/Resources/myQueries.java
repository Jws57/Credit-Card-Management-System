package Resources;


public class myQueries {
	/*1)	To display the transactions made by customers living in a given zipcode for a 
	 given month and year. Order by day in descending order.*/
	static final public String Customer_Details_1 = "select LAST_NAME, FIRST_NAME, TRANSACTION_VALUE " 
													+ " from cdw_sapp_creditcard as CR "
													+ "join cdw_sapp_customer as CU " + "on CR.CUST_SSN=CU.SSN "
													+ "where CR.YEAR = ? and CR.MONTH = ? and CU.CUST_ZIP= ? "
													+ "order by DAY ;";
	/*2)	To display the number and total values of transactions for a given type.*/
	static final public String 	Customer_Details_2 ="select TRANSACTION_TYPE, count(TRANSACTION_TYPE) as Number_Of_Transactions, sum(TRANSACTION_VALUE) as Total "
													+ "from cdw_sapp_creditcard "
													+ "where  TRANSACTION_TYPE= ?;";
	/*3)	To display the number and total values of transactions for branches in a given state*/

	static final public String 	Customer_Details_3="select count(TRANSACTION_ID) as Number_of_TRANSACTIONS,"
													+ " sum(TRANSACTION_VALUE) as Total_value "
													+ "from cdw_sapp_creditcard as "
													+ "CR join cdw_sapp_branch as B on CR.BRANCH_CODE=B.BRANCH_CODE "
													+ "Where BRANCH_STATE= ? ;";       
	
	/* 4)	To check the existing account details of a customer */
	
	static final public String 	Customer_Details_4="select * from cdw_sapp_customer where SSN = ? ;";
	
	
	/* 5)	To modify the existing account details of a customer */
	static final public String 	Customer_Details_5="update cdw_sapp_customer              "
													+ " set FIRST_NAME = COALESCE( ? , FIRST_NAME),"
													+ " MIDDLE_NAME =COALESCE(? , MIDDLE_NAME),"
													+ " LAST_NAME = COALESCE( ? , LAST_NAME),"
													+ " APT_NO=COALESCE( ? , APT_NO), "
													+ " STREET_NAME =COALESCE( ? ,  STREET_NAME),"
													+ " CUST_CITY= COALESCE( ? , CUST_CITY),"
													+ " CUST_STATE =COALESCE( ? , CUST_STATE),"
													+ " CUST_COUNTRY =COALESCE( ?  , CUST_COUNTRY),"
													+ " CUST_ZIP=COALESCE( ? , CUST_ZIP),"
													+ " CUST_PHONE = COALESCE( ? , CUST_PHONE),"
													+ " CUST_EMAIL=COALESCE( ? ,  CUST_EMAIL)"
													+ " where ssn= ? and CREDIT_CARD_NO = ? ;";
	/*6)	To generate monthly bill for a credit card number for a given month and year.*/
	
	static final public String 	Customer_Details_6="Select TRANSACTION_ID, DAY, TRANSACTION_TYPE, TRANSACTION_VALUE"
													+ " from cdw_sapp_creditcard"
													+ " where CREDIT_CARD_NO= ? "
													+ " and YEAR = ?"
													+ " and MONTH = ? "
													+ " order by day;"; 
	
	/* 7)	To display the transactions made by a customer between two dates. Order by year, month, and day in descending order. */
	static final public String 	Customer_Details_7="Select TRANSACTION_ID, MONTH, DAY, YEAR, TRANSACTION_TYPE, TRANSACTION_VALUE "
													+ " from cdw_sapp_creditcard"
													+ " where CUST_SSN = ? "
													+ " and STR_TO_DATE( CONCAT( MONTH, '-', DAY, '-', YEAR ),'%m-%d-%Y' )"
													+ " BETWEEN STR_TO_DATE( ? , '%m-%d-%Y' ) "
													+ " AND STR_TO_DATE( ? , '%m-%d-%Y')"
													+ " order by day, TRANSACTION_VALUE;";
													
}

