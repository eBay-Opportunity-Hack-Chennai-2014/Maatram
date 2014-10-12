package com.ngo.webservice.event;

public class EventForm {
	
	private String title;
	private String description;
	private String category;
	
	private String location;
	
	private String date;
	
	private String resourceAcquired;
	
	private String resourceNeeded;
	
	private int month;

	private int year;
	
	private int org_id;

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrg_id() {
		return org_id;
	}

	public void setOrg_id(int org_id) {
		this.org_id = org_id;
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

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	
}