package com.ngo.model;


import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="transaction")
public class Transaction {

	@Id
	@GenericGenerator(name="transaction_seq_gen" , strategy="increment")
	@GeneratedValue(generator="transaction_seq_gen")
	@Column(name = "id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private User fromUser;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private User toUser;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
	private Event event;
	
	@Column(name = "from_user_verification")
	private boolean fromVerification;
	
	@Column(name = "to_user_verification")
	private boolean toVerification;
	
	@Column(name = "to_passcode")
	private String toPasscode;
	
	@Column(name = "from_passcode")
	private String fromPasscode;
	
	public Transaction(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public boolean isFromVerification() {
		return fromVerification;
	}

	public void setFromVerification(boolean fromVerification) {
		this.fromVerification = fromVerification;
	}

	public boolean isToVerification() {
		return toVerification;
	}

	public void setToVerification(boolean toVerification) {
		this.toVerification = toVerification;
	}

	public String getToPasscode() {
		return toPasscode;
	}

	public void setToPasscode(String toPasscode) {
		this.toPasscode = toPasscode;
	}

	public String getFromPasscode() {
		return fromPasscode;
	}

	public void setFromPasscode(String fromPasscode) {
		this.fromPasscode = fromPasscode;
	}
	
}
