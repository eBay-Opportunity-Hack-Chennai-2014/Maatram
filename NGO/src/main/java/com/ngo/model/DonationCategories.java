package com.ngo.model;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="donationcategories")
public class DonationCategories {

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDonationDescription() {
	return donationDescription;
}
public void setDonationDescription(String donationDescription) {
	this.donationDescription = donationDescription;
}
public String getSummary() {
	return summary;
}
public void setSummary(String summary) {
	this.summary = summary;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
@Id
@GenericGenerator(name="donation_seq_gen" , strategy="increment")
@GeneratedValue(generator="donation_seq_gen")
@Column(name = "id")
private int id;
private String donationDescription;
private String summary;
private boolean active;
private String path;



}