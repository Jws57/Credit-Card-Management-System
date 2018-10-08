package DAO_Interfaces;

import java.util.List;

import ValueObject.TransactionDetails;

public interface TransactionDetails_DAO {
	 	public void insert(TransactionDetails TransactionDetails);
	    public List<TransactionDetails> getTotalAndCountOfTransactionType();
	    public List<TransactionDetails>  getCustomersTransactionsFromZipcodeInaMonthAndYear();
	    public List<TransactionDetails> getNumberAndTotalvaluesOftransactionsInBranchesInaState();
	    
	    
}
