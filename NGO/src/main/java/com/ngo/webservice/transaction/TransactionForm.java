package com.ngo.webservice.transaction;

import java.sql.Date;

public class TransactionForm {

	private int fromUser;
	private int toUser;
	private int event;
	private double amount;
	private Date date;
	
	public int getFromUser() {
		return fromUser;
	}
	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}
	public int getToUser() {
		return toUser;
	}
	public void setToUser(int toUser) {
		this.toUser = toUser;
	}
	public int getEvent() {
		return event;
	}
	public void setEvent(int event) {
		this.event = event;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
