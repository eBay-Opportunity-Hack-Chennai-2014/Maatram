package com.ngo.webservice.donation;

public class Contribution {
 int eventId;
 int userId;
 int amount;
 String email_id;
 String address;
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getEventId() {
	return eventId;
}
public void setEventId(int eventId) {
	this.eventId = eventId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public String getEmail_id() {
	return email_id;
}
public void setEmail_id(String email_id) {
	this.email_id = email_id;
}
 
}
