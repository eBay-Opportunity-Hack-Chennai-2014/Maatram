package com.ngo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="event")
public class Event {

	@Id
	@GenericGenerator(name="event_seq_gen" , strategy="increment")
	@GeneratedValue(generator="event_seq_gen")
	@Column(name = "id")
	private int id; //event id
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "resourceacquired")
	private String resourceAcquired;
	
	@Column(name = "resourceneeded")
	private String resourceNeeded;
	
	@Column(name = "month")
	private int month;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "org_id")
	private int org_id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
	private Organization organization;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="organization_user",joinColumns={@JoinColumn(name="organization_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
	private List<User> users = new ArrayList<User>();
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="event_contribution",joinColumns={@JoinColumn(name="event_id")},inverseJoinColumns={@JoinColumn(name="contribution_id")})
	private List<Contribution> contributions = new ArrayList<Contribution>();
	
	public int getOrg_id() {
		return org_id;
	}

	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Event(){}

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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Contribution> getContributions() {
		return contributions;
	}

	public void setContributions(List<Contribution> contributions) {
		this.contributions = contributions;
	}

}
