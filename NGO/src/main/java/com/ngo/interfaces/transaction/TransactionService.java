package com.ngo.interfaces.transaction;

import java.util.List;

import com.ngo.model.Transaction;
import com.ngo.webservice.transaction.TransactionForm;
import com.ngo.webservice.transaction.VerificationForm;

public interface TransactionService {

	//Core User functions
	public int addTransaction(TransactionForm userForm);
	public int deleteTransaction(int id);
	
	//Utility functions
	public List<Transaction> getTransactions();
	public boolean checkVerification(int id, VerificationForm verificationForm);
	
}
