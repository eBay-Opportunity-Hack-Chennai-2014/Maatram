package com.ngo.interfaces.transaction;

import com.ngo.model.Transaction;
import com.ngo.model.User;

public interface TransactionDao {

	public int addTransaction(Transaction transaction);
	public int modifyTransaction(Transaction transaction);
	public int deleteTransaction(Transaction transaction);
	
}
