package com.ngo.webservice.event;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.ngo.model.Contribution;
import com.ngo.model.User;

public class EventForm {
	
	private int id; //event id
	
	private String category;
	
	private String location;
	
	private String date;
	
	private String resourceAcquired;
	
	private String resourceNeeded;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResourceAcquired() {
		return resourceAcquired;
	}

	public void setResourceAcquired(String resourceAcquired) {
		this.resourceAcquired = resourceAcquired;
	}

	public String getResourceNeeded() {
		return resourceNeeded;
	}

	public void setResourceNeeded(String resourceNeeded) {
		this.resourceNeeded = resourceNeeded;
	}
	
}