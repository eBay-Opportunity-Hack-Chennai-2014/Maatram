package com.ngo.implementation.transaction;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.transaction.TransactionDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Transaction;

/**
 * @author Sai Pranav
 *
 */

@Repository
public class TransactionDaoImpl implements TransactionDao{
	
	@Autowired
	UtilityDao utilityDao;

	public int addTransaction(Transaction transaction) {
		Session session = utilityDao.getSession();
		try {
			session.save(transaction);
		}catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return transaction.getId();
	}
	
	public int modifyTransaction(Transaction transaction) {
		Session session = utilityDao.getSession();
		try{
			session.update(transaction);
		}catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return transaction.getId();
	}

	public int deleteTransaction(Transaction transaction) {
		Session session = utilityDao.getSession();
		try{
			session.delete(transaction);
		}catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return transaction.getId();
	}

}
