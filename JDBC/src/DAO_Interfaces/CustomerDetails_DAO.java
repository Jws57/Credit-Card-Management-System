package DAO_Interfaces;

import java.text.ParseException;
import java.util.List;

import ValueObject.CustomerDetails;
import ValueObject.TransactionDetails;

public interface CustomerDetails_DAO {
	 	
	    
	    public List<CustomerDetails> getAccountDetailsOfaCustomer();
	    public int modifyAccountDetailsOfaCustomer();
	    public List<TransactionDetails> getMonthlyBillForCreditCardForMonthAndYear();
	    public List<TransactionDetails>  getCustomerTransactionsBetweenTwoDates () throws ParseException;
}
