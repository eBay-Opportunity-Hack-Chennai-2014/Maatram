package com.ngo.webservice.donation;

import org.json.JSONObject;

public class Students {
private int id;
private String name;
private String description;
private String education;
private int moneyRequired;
private int moneyRaised;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getEducation() {
	return education;
}
public void setEducation(String education) {
	this.education = education;
}
public int getMoneyRequired() {
	return moneyRequired;
}
public void setMoneyRequired(int moneyRequired) {
	this.moneyRequired = moneyRequired;
}
public int getMoneyRaised() {
	return moneyRaised;
}
public void setMoneyRaised(int moneyRaised) {
	this.moneyRaised = moneyRaised;
}

public JSONObject getJSON(){
	JSONObject j = new JSONObject();
	j.put("id", this.getId());
	j.put("name", this.getName());
	j.put("description", this.getDescription());
	j.put("moneyRaised", this.getMoneyRaised());
	j.put("moneyRequired", this.getMoneyRequired());
	j.put("education",this.getEducation());
	j.put("type", "educate");
	return j;
}



}
