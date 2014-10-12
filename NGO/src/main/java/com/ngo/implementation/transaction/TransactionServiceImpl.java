package com.ngo.implementation.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.transaction.TransactionDao;
import com.ngo.interfaces.transaction.TransactionService;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Event;
import com.ngo.model.Transaction;
import com.ngo.model.User;
import com.ngo.webservice.transaction.TransactionForm;
import com.ngo.webservice.transaction.VerificationForm;

@Component
@Transactional
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	UtilityDao utilityDao;
	
	/*User functions*/
	public int addTransaction(TransactionForm transactionForm){
		User fromUser = utilityDao.getUser(transactionForm.getFromUser());
		if(fromUser == null){
			throw new NGOException("From User does not exist");
		}
		User toUser = utilityDao.getUser(transactionForm.getToUser());
		if(toUser == null){
			throw new NGOException("To User does not exist");
		}
		Event event = utilityDao.getEvent(transactionForm.getEvent());
		if(event == null){
			throw new NGOException("Event does not exist");
		}
		Transaction transaction = new Transaction();
		transaction.setFromUser(fromUser);
		transaction.setAmount(transactionForm.getAmount());
		transaction.setDate(transactionForm.getDate());
		transaction.setEvent(event);
		transaction.setToUser(toUser);
		transaction.setFromPasscode(Double.toString(Math.random()));
		transaction.setToPasscode(Double.toString(Math.random()));
		transaction.setFromVerification(false);
		transaction.setToVerification(false);
		return transactionDao.addTransaction(transaction);
	}

	@Override
	public int deleteTransaction(int transactionId){
		Transaction transaction = utilityDao.getTransaction(transactionId);
		if(transaction == null){
			throw new NGOException("Transaction does not exist");
		}
		return transactionDao.deleteTransaction(transaction);
	}

	@Override
	public List<Transaction> getTransactions() {
		return utilityDao.getAllTransaction();
	}
	
	@Override
	public boolean checkVerification(int id,VerificationForm verificationForm){
		Transaction transaction = utilityDao.getTransaction(id);
		if(transaction == null){
			throw new NGOException("Transaction does not exist");
		}
		if( (transaction.getFromUser().getId() == verificationForm.getUserId()) || (transaction.getToUser().getId() == verificationForm.getUserId()) ){
			if(transaction.getFromUser().getId() == verificationForm.getUserId()){
				if(transaction.getFromPasscode().equals(verificationForm.getPasscode())){
					transaction.setFromVerification(true);
					transactionDao.modifyTransaction(transaction);
					return true;
				}
				else if(transaction.getToPasscode().equals(verificationForm.getPasscode())){
					transaction.setToVerification(true);
					transactionDao.modifyTransaction(transaction);
					return true;
				}
				else{
					throw new NGOException("Passcode does not match");
				}
			}
		}
		else{
			throw new NGOException("User not associcated with the Transaction");
		}
		return false;
	}

	
}
