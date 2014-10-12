package com.ngo.webservice.transaction;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ngo.exception.RESTException;
import com.ngo.interfaces.transaction.TransactionService;
import com.ngo.interfaces.user.UserService;
import com.ngo.model.Transaction;
import com.ngo.model.User;

@Component
@Path("/transaction")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionWebService {
	
	@Autowired
	TransactionService transactionService;
	
	@GET
	public List<Transaction> listTransactions() {
		return transactionService.getTransactions();
	}

	@POST
	public Response addTranaction(TransactionForm transactionForm) {
		int tempId;
		try{
			tempId = transactionService.addTransaction(transactionForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteTransaction(@PathParam("id") int id) {
		int tempId;
		try{
			tempId = transactionService.deleteTransaction(id);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(tempId).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response verifyTransaction(@PathParam("id") int id,VerificationForm verificationForm) {
		boolean result;
		try{
			result =  transactionService.checkVerification(id, verificationForm);
		}
		catch(Exception e){
			throw new RESTException(e.getMessage());
		}
		return Response.status(200).entity(result).build();
	}
	
}
